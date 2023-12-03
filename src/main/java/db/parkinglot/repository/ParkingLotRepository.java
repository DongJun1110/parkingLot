package db.parkinglot.repository;

import db.parkinglot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    List<ParkingLot> findParkingLotByNameContaining(String keyword);
    List<ParkingLot> findParkingLotByLocationContaining(String keyword);

}
