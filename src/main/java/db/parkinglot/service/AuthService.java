package db.parkinglot.service;

import db.parkinglot.dto.UserInfoDto;
import db.parkinglot.dto.UserJoinDto;
import db.parkinglot.dto.UserLoginDto;
import db.parkinglot.entity.Member;
import db.parkinglot.security.TokenInfo;

public interface AuthService {
    TokenInfo login(UserLoginDto userLoginDto);
    Member join(UserJoinDto userJoinDto);
    UserInfoDto info();
}