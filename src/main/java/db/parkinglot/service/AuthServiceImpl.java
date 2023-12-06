package db.parkinglot.service;

import db.parkinglot.dto.UserInfoDto;
import db.parkinglot.dto.UserJoinDto;
import db.parkinglot.dto.UserLoginDto;
import db.parkinglot.entity.Member;
import db.parkinglot.entity.Role;
import db.parkinglot.entity.Vehicle;
import db.parkinglot.repository.MemberRepository;
import db.parkinglot.security.JwtTokenProvider;
import db.parkinglot.security.SecurityUtil;
import db.parkinglot.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenInfo login(UserLoginDto userLoginDto) {

        Member member = memberRepository.findByUserId(userLoginDto.getUserId()).orElseThrow(()
                -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));

        boolean matches = passwordEncoder.matches(userLoginDto.getPassword(), member.getPassword());
        if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getUserId(), member.getPassword(), member.getAuthorities());

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        tokenInfo.setEmail(member.getUserId());
        tokenInfo.setMemberRole(member.getRole().toString());

        // Set the authentication in JwtTokenProvider
        jwtTokenProvider.setAuthentication(authentication);
        SecurityUtil.getCurrentMemberId();
        System.out.println("login 안에서 SecurityUtil.getCurrentMemberId() = " + SecurityUtil.getCurrentMemberId());
        System.out.println("login안에서 tokenInfo = " + tokenInfo);

        return tokenInfo;
    }

    @Override
    public Member join(UserJoinDto userJoinDto){

        Optional<Member> byUserId = memberRepository.findByUserId(userJoinDto.getUserId());
        if(byUserId.isPresent()){
            return null;
        }

        Member member = Member.builder()
                .userId(userJoinDto.getUserId())
                .password(passwordEncoder.encode(userJoinDto.getPassword()))
                .role(Role.USER)
                .username(userJoinDto.getUserName())
                .email(userJoinDto.getEmail())
                .phoneNumber(userJoinDto.getPhoneNumber())
                .createAt(new Date())
                .build();

        return memberRepository.save(member);

    }

    @Override
    public UserInfoDto info() {
        UserInfoDto userInfoDto = SecurityUtil.getCurrentMemberId();
        System.out.println("info 서비스안에서 userInfoDto = " + userInfoDto);
        return userInfoDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRole().toString())
                .build();
    }
}