package db.parkinglot.controller;

import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping("/lists")
    @ResponseBody
    public List<ParkingLotResponseDto> lists() {
        return parkingLotService.getParkingLotLists();
    }

}
