package db.parkinglot.entity.reservation;

import db.parkinglot.entity.Chauffeur;
import db.parkinglot.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChauffeurReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Chauffeur chauffeur;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String date;
    private String carNumber;
    private String pickupLocation;
    private String pickupTime;
    private String destination;

}
