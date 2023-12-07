package db.parkinglot.repository;

import db.parkinglot.entity.Member;
import db.parkinglot.entity.reservation.ChauffeurReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationChauffeurRepository extends JpaRepository<ChauffeurReservation, Long> {
    List<ChauffeurReservation> findChauffeurReservationByMemberId(Long memberId);
    List<ChauffeurReservation> findChauffeurReservationByMember(Member member);


}
