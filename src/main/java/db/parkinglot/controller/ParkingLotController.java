package db.parkinglot.controller;

import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parkingLots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping("/lists")
    public String lists(Model model) {

        List<ParkingLotResponseDto> parkingLots = parkingLotService.getParkingLotLists();
        model.addAttribute("parkingLots", parkingLots);
        return "parkinglot/parkinglotList";
    }

    @GetMapping("/{parkingLotId}")
    @ResponseBody
    public ParkingLot detail(@PathVariable Long parkingLotId) {
        return parkingLotService.getParkingLot(parkingLotId);
    }

    @GetMapping("/search")
    public String searchParkingLots(@RequestParam String keyword, Model model) {
        List<ParkingLotResponseDto> parkingLots = parkingLotService.searchParkingLots(keyword);
        model.addAttribute("parkingLots", parkingLots);
        return "parkinglot/searchedList";
    }
}
