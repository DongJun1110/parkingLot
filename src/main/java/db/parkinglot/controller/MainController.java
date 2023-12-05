package db.parkinglot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/parkingList")
    @ResponseBody
    public String parkingList() {
        return "parkingList";
    }

    @ResponseBody
    @GetMapping("/parkingRegister")
    public String parkingRegister() {
        return "parkingRegister";
    }

    @ResponseBody
    @GetMapping("/myPage")
    public String my() {
        return "마이페이지";
    }

}
