package db.parkinglot.controller;

import db.parkinglot.dto.ParkingLotRequestDto;
import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registerParkingLot")
public class ParkingLotRegisterController {

    private final ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<String> saveParkingLot(@RequestBody ParkingLotRequestDto pld) {
        parkingLotService.registerParkingLot(pld);
        return ResponseEntity.ok("Parking Lot added successfully");
    }

}
