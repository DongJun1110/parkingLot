package db.parkinglot.service;

import db.parkinglot.dto.VehicleRequestDto;
import db.parkinglot.entity.Member;
import db.parkinglot.entity.Vehicle;
import db.parkinglot.repository.MemberRepository;
import db.parkinglot.repository.VehicleRepository;
import db.parkinglot.security.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {

    private final MemberRepository memberRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public List<Vehicle> getVehicles() {

        String userId = SecurityUtil.getCurrentMemberId().getUserId();
        Optional<Member> foundMember = memberRepository.findByUserId(userId);

        if(foundMember.isPresent()){
            Member member = foundMember.get();

            log.info("등록 차량" + member.getVehicle());

            return member.getVehicle();
        }
        return null;
    }

    @Transactional
    public Vehicle registerVehicle(VehicleRequestDto vehicleRequestDto) {

        String userId = SecurityUtil.getCurrentMemberId().getUserId();
        Optional<Member> foundMember = memberRepository.findByUserId(userId);

        if(foundMember.isPresent()){

            Member member = foundMember.get();

            Vehicle vehicle = Vehicle.builder()
                    .color(vehicleRequestDto.getColor())
                    .brand(vehicleRequestDto.getBrand())
                    .number(vehicleRequestDto.getNumber())
                    .createdAt(new Date())
                    .member(member)
                    .build();

            member.getVehicle().add(vehicle);

            memberRepository.save(member);

            return vehicleRepository.save(vehicle);
        }

        return null;
    }
}
