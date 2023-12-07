package db.parkinglot.controller;

import db.parkinglot.dto.ChauffeurRegisterRequestDto;
import db.parkinglot.dto.reserveDto.ChauffeurReservationRequestDto;
import db.parkinglot.entity.Chauffeur;
import db.parkinglot.entity.reservation.ChauffeurReservation;
import db.parkinglot.service.ChauffeurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chauffeur")
public class ChauffeurController {

    private final ChauffeurService chauffeurService;

    //예약 폼
    @GetMapping("/reservation")
    public String getReservationForm() {
        return "chauffeur/reservationForm";
    }

    //대리기사 목록
    @GetMapping("/lists")
    public String getChauffeurList(Model model) {
        List<Chauffeur> chauffeurs = chauffeurService.showChauffeurList();
        model.addAttribute("chauffeurs", chauffeurs);
        return "chauffeur/list";
    }

    //대리 예약
    @PostMapping("/reservation/{chauffeurId}")
    @ResponseBody
    public HttpStatus reserveChauffeur(@PathVariable Long chauffeurId,
                                       @RequestBody ChauffeurReservationRequestDto dto) {
        ChauffeurReservation chauffeurReservation = chauffeurService.reserveChauffeur(chauffeurId, dto);
        log.info("디티오"+chauffeurReservation.toString());
        if (chauffeurReservation == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    //대리기사 예약 목록
    @GetMapping("/reservationList")
    @ResponseBody
    public List<ChauffeurReservation> showReservationList() {
        return chauffeurService.showReservationList();
    }

    //대리 찾기
    @GetMapping("/search")
    public String searchChauffeur(@RequestParam String keyword, Model model) {
        List<Chauffeur> chauffeurs = chauffeurService.searchChauffeur(keyword);
        model.addAttribute("chauffeurs", chauffeurs);
        return "parkinglot/searchedList";
    }

    //대리기사 등록하기
    @PostMapping("/registerDriver")
    @ResponseBody
    public Chauffeur registerDriver(@RequestBody ChauffeurRegisterRequestDto dto) {
        return chauffeurService.registerDriver(dto);
    }
}