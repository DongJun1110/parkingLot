package db.parkinglot.controller;

import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping("/lists")
    public List<ParkingLotResponseDto> lists() {
        return parkingLotService.getParkingLotLists();
    }

    @GetMapping("/{parkingLotId}")
    public ParkingLot detail(@PathVariable Long parkingLotId) {
        return parkingLotService.getParkingLot(parkingLotId);
    }

}
