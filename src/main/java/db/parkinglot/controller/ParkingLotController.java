package db.parkinglot.controller;

import db.parkinglot.dto.ParkingLotReservationResponseDto;
import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parkingLots")
@Slf4j
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @GetMapping("/lists")
    public String lists(Model model) {

        List<ParkingLotResponseDto> parkingLots = parkingLotService.getParkingLotLists();
        model.addAttribute("parkingLots", parkingLots);
        return "parkinglot/parkinglotList";
    }

    @GetMapping("/{parkingLotId}")
    public String detail(Model model, @PathVariable Long parkingLotId) {
        ParkingLot parkingLot = parkingLotService.getDetail(parkingLotId);
        model.addAttribute("detailParkingLot", parkingLot);
        return "parkinglot/detail";
    }

    @GetMapping("/search")
    public String searchParkingLots(@RequestParam String keyword, Model model) {
        List<ParkingLotResponseDto> parkingLots = parkingLotService.searchParkingLots(keyword);
        model.addAttribute("parkingLots", parkingLots);
        return "parkinglot/searchedList";
    }

    @GetMapping("/{parkingLotId}/reservation")
    public String reservationForm(@PathVariable Long parkingLotId) {
        Integer availableSpace = parkingLotService.getAvailableSpace(parkingLotId);
        if (availableSpace == null) {
            return null;
        }
        return "reservation/reservationForm";
    }

    @PostMapping("/{parkingLotId}/reservation")
    @ResponseBody
    public String reservation(@PathVariable Long parkingLotId) {

        ParkingLot parkingLot = parkingLotService.reserveParkingLot(parkingLotId);
        if (parkingLot == null) {
            return "bad";
        }
        return "ok";
    }

    @ResponseBody
    @GetMapping("/reservationList")
    public List<ParkingLotReservationResponseDto> showParkingLotReservationList() {
        List<ParkingLotReservationResponseDto> result = parkingLotService.showReservationList();
        return result;
    }

}
