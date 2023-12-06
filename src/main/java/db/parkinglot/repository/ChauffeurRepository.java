package db.parkinglot.repository;

import db.parkinglot.entity.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {

}
