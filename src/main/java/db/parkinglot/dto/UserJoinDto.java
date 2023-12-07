package db.parkinglot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserJoinDto {

    private String userId;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;

}