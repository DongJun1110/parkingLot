package db.parkinglot.controller;

import db.parkinglot.dto.ChauffeurRegisterRequestDto;
import db.parkinglot.entity.Chauffeur;
import db.parkinglot.service.ChauffeurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
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
    public Chauffeur reserveChauffeur(@PathVariable Long chauffeurId) {
        Chauffeur chauffeur = chauffeurService.reserveChauffeur(chauffeurId);
        if (chauffeur == null) {
            return null;
        }
        return chauffeur;
    }

    //대리기사 예약 목록
    @GetMapping("/reservationList")
    @ResponseBody
    public List<Chauffeur> showReservationList() {
        return chauffeurService.showReservationList();
    }


    //대리기사 등록하기
    @PostMapping("/registerDriver")
    @ResponseBody
    public Chauffeur registerDriver(@RequestBody ChauffeurRegisterRequestDto dto) {
        return chauffeurService.registerDriver(dto);
    }
}
