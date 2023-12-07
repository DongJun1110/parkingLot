package db.parkinglot.service;

import db.parkinglot.dto.ChauffeurRegisterRequestDto;
import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.dto.reserveDto.ChauffeurReservationRequestDto;
import db.parkinglot.entity.Chauffeur;
import db.parkinglot.entity.Member;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.entity.reservation.ChauffeurReservation;
import db.parkinglot.repository.ChauffeurRepository;
import db.parkinglot.repository.MemberRepository;
import db.parkinglot.security.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<Chauffeur> showChauffeurList() {
        List<Chauffeur> chauffeurs = chauffeurRepository.findAll();
        return chauffeurs;
    }

    //대리 예약 서비스
    @Transactional
    public ChauffeurReservation reserveChauffeur(Long chauffeurId, ChauffeurReservationRequestDto dto) {

        String userId = SecurityUtil.getCurrentMemberId().getUserId();
        Optional<Member> foundMember = memberRepository.findByUserId(userId);

        Optional<Chauffeur> foundChauffeur = chauffeurRepository.findById(chauffeurId);

        if(foundMember.isPresent() && foundChauffeur.isPresent()){

            Member member = foundMember.get();
            Chauffeur chauffeur = foundChauffeur.get();

            ChauffeurReservation chauffeurReservation = ChauffeurReservation.builder()
                    .chauffeur(chauffeur)
                    .member(member)
                    .carNumber(dto.getCarNumber())
                    .date(dto.getDate())
                    .pickupTime(dto.getPickupTime())
                    .pickupLocation(dto.getPickupLocation())
                    .destination(dto.getDestination())
                    .build();

            return chauffeurReservation;
        }
        return null;
    }


    @Transactional
    public List<Chauffeur> showReservationList() {
        String userId = SecurityUtil.getCurrentMemberId().getUserId();
        Optional<Member> foundMember = memberRepository.findByUserId(userId);

        if (foundMember.isPresent()) {
            Member member = foundMember.get();
            List<Chauffeur> chauffeurs = member.getChauffeurs();
            return chauffeurs;
        }
        return null;
    }


    // 대리기사 등록하기
    @Transactional
    public Chauffeur registerDriver(ChauffeurRegisterRequestDto dto) {

        Chauffeur driver = Chauffeur.builder()
                .name(dto.getName())
                .area(dto.getArea())
                .drivingCareer(dto.getDrivingCareer())
                .licenseNumber(dto.getLicenseNumber())
                .build();

        return chauffeurRepository.save(driver);
    }

    @Transactional
    public List<Chauffeur> searchChauffeur(String keyword) {

        List<Chauffeur> plByArea = chauffeurRepository.findChauffeurByArea(keyword);
        List<Chauffeur> result = new ArrayList<>();

        for (Chauffeur pl : plByArea) {
            result.add(pl);
        }

        return result;
    }



}
