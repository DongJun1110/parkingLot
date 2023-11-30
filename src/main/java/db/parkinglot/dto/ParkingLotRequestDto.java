package db.parkinglot.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLotRequestDto {

    private String name;
    private String sort;
    private String location;
    private int totalSpace;
    private String startTime;
    private String endTime;
    private String fee;
    private int feePerHour;
    private int dayFee;
    private int monthFee;
    private String significant;
    private String company;
    private String contactNumber;

}
