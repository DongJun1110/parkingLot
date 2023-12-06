package db.parkinglot.controller;

import db.parkinglot.dto.UserInfoDto;
import db.parkinglot.entity.Member;
import db.parkinglot.repository.MemberRepository;
import db.parkinglot.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main/index";
    }

    @GetMapping("/parkingList")
    public String parkingList() {
        return "parkingList";
    }

    @GetMapping("/parkingRegister")
    public String parkingRegister() {
        return "register/register";
    }

    @GetMapping("/myPage")
    public String my() {
        return "my/myPage";
    }

}
