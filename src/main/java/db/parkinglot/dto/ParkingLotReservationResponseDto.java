package db.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotReservationResponseDto {

    private String name;
    private String location;
    private String company;
    private String sort;
    private String fee;

}
