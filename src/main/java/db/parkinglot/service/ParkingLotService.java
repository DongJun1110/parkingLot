package db.parkinglot.service;

import db.parkinglot.dto.ParkingLotRequestDto;
import db.parkinglot.dto.ParkingLotReservationResponseDto;
import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.dto.UserInfoDto;
import db.parkinglot.entity.Member;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.repository.MemberRepository;
import db.parkinglot.repository.ParkingLotRepository;
import db.parkinglot.security.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final MemberRepository memberRepository;

    //주차장 등록하기
    @Transactional
    public ParkingLot registerParkingLot(ParkingLotRequestDto pld) {

        if (pld != null) {
            ParkingLot pl = ParkingLot.builder()
                    .fee(pld.getFee())
                    .feePerHour(pld.getFeePerHour())
                    .dayFee(pld.getDayFee())
                    .name(pld.getName())
                    .location(pld.getLocation())
                    .monthFee(pld.getMonthFee())
                    .sort(pld.getSort())
                    .contactNumber(pld.getContactNumber())
                    .startTime(pld.getStartTime())
                    .endTime(pld.getEndTime())
                    .totalSpace(pld.getTotalSpace())
                    .leftSpace(pld.getTotalSpace())
                    .company(pld.getCompany())
                    .significant(pld.getSignificant())
                    .build();

            return parkingLotRepository.save(pl);
        }
        return null;
    }

    @Transactional
    public List<ParkingLotResponseDto> getParkingLotLists() {

        List<ParkingLot> foundParkingLots = parkingLotRepository.findAll();
        List<ParkingLotResponseDto> plDto = new ArrayList<>();

        for (ParkingLot pl : foundParkingLots) {
            plDto.add(ParkingLotResponseDto.toDto(pl));
        }
        return plDto;
    }

    @Transactional
    public List<ParkingLotResponseDto> searchParkingLots(String keyword) {
        List<ParkingLot> plByName =
                parkingLotRepository.findParkingLotByNameContaining(keyword);

        List<ParkingLot> plByLocation =
                parkingLotRepository.findParkingLotByLocationContaining(keyword);

        List<ParkingLotResponseDto> result = new ArrayList<>();

        for (ParkingLot pl : plByName) {
            result.add(ParkingLotResponseDto.toDto(pl));
        }

        for (ParkingLot pl : plByLocation) {
            result.add(ParkingLotResponseDto.toDto(pl));
        }

        return result;
    }

    @Transactional
    public ParkingLot getDetail(Long parkingLotId) {
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);
        ParkingLot foundParkingLot = parkingLot.get();
        if (foundParkingLot != null) {
            return foundParkingLot;
        }
        return null;
    }

    @Transactional
    public Integer getAvailableSpace(Long ParkingLotId) {
        Optional<ParkingLot> foundParkingLot = parkingLotRepository.findById(ParkingLotId);

        if(foundParkingLot.isPresent()){
            ParkingLot parkingLot = foundParkingLot.get();

            return parkingLot.getLeftSpace();
        }
        return null;
    }

    @Transactional
    public ParkingLot reserveParkingLot(Long parkingLotId) {

        Optional<ParkingLot> foundParkingLot = parkingLotRepository.findById(parkingLotId);
        List<ParkingLot> result = new ArrayList<>();

        if(foundParkingLot.isPresent()){
            ParkingLot parkingLot = foundParkingLot.get();
            UserInfoDto currentMemberId = SecurityUtil.getCurrentMemberId();
            Optional<Member> foundMember = memberRepository.findByUserId(currentMemberId.getUserId());
            result.add(parkingLot);


            if(foundMember.isPresent()){
                Member member = foundMember.get();
                parkingLot.setMember(member);

                member.getParkingLot().add(parkingLot);
                memberRepository.save(member);

                parkingLot.setLeftSpace(parkingLot.getLeftSpace()-1);
                parkingLotRepository.save(parkingLot);

                return parkingLot;
            }
        }
        return null;
    }

    @Transactional
    public List<ParkingLotReservationResponseDto> showReservationList() {

        String userId = SecurityUtil.getCurrentMemberId().getUserId();
        Optional<Member> foundMember = memberRepository.findByUserId(userId);
        List<ParkingLotReservationResponseDto> result = new ArrayList<>();

        if(foundMember.isPresent()){
            Member member = foundMember.get();
            List<ParkingLot> parkingLots = member.getParkingLot();
            log.info("현재 사용자"+ member.getUsername());


            for (ParkingLot pl : parkingLots) {
                ParkingLotReservationResponseDto dto = ParkingLotReservationResponseDto.builder()
                        .name(pl.getName())
                        .sort(pl.getSort())
                        .location(pl.getLocation())
                        .company(pl.getCompany())
                        .fee(pl.getFee())
                        .build();
                result.add(dto);
            }

            log.info("예약목록"+result);
            return result;
        }
        return null;
    }
}
