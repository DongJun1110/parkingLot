package db.parkinglot.repository;

import db.parkinglot.entity.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
    List<Chauffeur> findChauffeurByArea(String keyword);
}
