package db.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChauffeurReservationRequestDto {

    private String carNumber;
    private String pickupLocation;
    private String pickupTime;
    private String arriveLocation;

}
