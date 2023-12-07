package db.parkinglot.dto.reserveDto;

import lombok.*;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChauffeurReservationRequestDto {

    private String carNumber;
    private String date;
    private String pickupLocation;
    private String pickupTime;
    private String destination;

}
