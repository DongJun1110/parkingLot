package db.parkinglot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/MyPageForm")
    public String getMyPage() {
        return "my/myPage";
    }
}
