package db.parkinglot.dto.reserveDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotReservationDto {

    private String date;
    private String enterTime;
    private String exitTime;

}
