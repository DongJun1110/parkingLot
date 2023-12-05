package db.parkinglot.controller;

import db.parkinglot.dto.UserJoinDto;
import db.parkinglot.dto.UserLoginDto;
import db.parkinglot.entity.Member;
import db.parkinglot.security.TokenInfo;
import db.parkinglot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "auth/join";
    }

    @PostMapping("/login")
    @ResponseBody
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto) {
        return authService.login(userLoginDto);
    }

    @PostMapping("/join")
    @ResponseBody
    public Member join(@RequestBody UserJoinDto userJoinDto) {

        Member joinMember = authService.join(userJoinDto);
        if (joinMember == null) {
            return null;
        }
        return joinMember;
    }

}
