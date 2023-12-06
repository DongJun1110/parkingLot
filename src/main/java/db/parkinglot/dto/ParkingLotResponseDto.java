package db.parkinglot.dto;

import db.parkinglot.entity.ParkingLot;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotResponseDto {

    private Long id;
    private String name;
    private String location;
    private int totalSpace;
    private int leftSpace;
    private String company;
    private String fee;
    private String sort;
    private String start_time;
    private String end_time;

    public static ParkingLotResponseDto toDto(ParkingLot parkingLot) {
        return ParkingLotResponseDto.builder()
                .id(parkingLot.getId())
                .name(parkingLot.getName())
                .location(parkingLot.getLocation())
                .totalSpace(parkingLot.getTotalSpace())
                .leftSpace(parkingLot.getLeftSpace())
                .company(parkingLot.getCompany())
                .fee(parkingLot.getFee())
                .sort(parkingLot.getSort())
                .start_time(parkingLot.getStartTime())
                .end_time(parkingLot.getEndTime())
                .build();
    }
}
