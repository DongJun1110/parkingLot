package db.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {

    private String brand;
    private String color;
    private String number;

}
