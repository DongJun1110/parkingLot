package db.parkinglot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    @GetMapping("/MyPageForm")
    public String getMyPage() {
        return "my/myPage";
    }

    @GetMapping("/MyPageParkingLotReservationForm")
    public String getParkingLotReservationForm() {
        return "my/parkingLotReserveList";
    }

    @GetMapping("/MyPageChauffeurReservationForm")
    public String getChauffeurReservationForm() {
        return "my/chauffeurReserveList";
    }
}
