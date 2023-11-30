package db.parkinglot.service;

import db.parkinglot.dto.ParkingLotRequestDto;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.repository.ParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    //주차장 등록하기
    public ParkingLot registerParkingLot(ParkingLotRequestDto pld) {

        ParkingLot pl = ParkingLot.builder()
                .fee(pld.getName())
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
                .company(pld.getCompany())
                .significant(pld.getSignificant()).build();

        ParkingLot savedParkingLot = parkingLotRepository.save(pl);
        return savedParkingLot;

    }

}
