package db.parkinglot.service;

import db.parkinglot.dto.ParkingLotRequestDto;
import db.parkinglot.dto.ParkingLotResponseDto;
import db.parkinglot.entity.ParkingLot;
import db.parkinglot.repository.ParkingLotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

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
    public ParkingLot getParkingLot(Long parkingLotId) {

        Optional<ParkingLot> foundParkingLot = parkingLotRepository.findById(parkingLotId);
        ParkingLot parkingLot = foundParkingLot.get();

        return parkingLot;
    }

}
