package db.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChauffeurRegisterRequestDto {

    private String name;

    private String area;

    private String licenseNumber;

    private String drivingCareer;
}
