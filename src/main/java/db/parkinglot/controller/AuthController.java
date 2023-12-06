package db.parkinglot.controller;

import db.parkinglot.dto.UserJoinDto;
import db.parkinglot.dto.UserLoginDto;
import db.parkinglot.entity.Member;
import db.parkinglot.security.TokenInfo;
import db.parkinglot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "auth/join";
    }

    @PostMapping("/login")
    @ResponseBody
    public HttpStatus login(@RequestBody UserLoginDto userLoginDto) {
        TokenInfo info = authService.login(userLoginDto);
        if (info == null) {
            return HttpStatus.BAD_GATEWAY;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/join")
    @ResponseBody
    public HttpStatus join(@RequestBody UserJoinDto userJoinDto) {

        Member joinMember = authService.join(userJoinDto);
        if (joinMember == null) {
            return HttpStatus.BAD_GATEWAY;
        }
        return HttpStatus.OK;
    }

}
