package db.parkinglot.repository;

import db.parkinglot.entity.reservation.ChauffeurReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationChauffeurRepository extends JpaRepository<ChauffeurReservation, Long> {

}
