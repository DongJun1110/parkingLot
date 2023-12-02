package db.parkinglot.controller;

import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    private ParkingLotService parkingLotService;


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
        return "myPage";
    }

}
