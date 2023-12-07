package db.parkinglot.repository;

import db.parkinglot.entity.reservation.ParkingLotReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationParkingLotRepository extends JpaRepository<ParkingLotReservation, Long> {

}
