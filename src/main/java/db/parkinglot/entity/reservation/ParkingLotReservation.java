package db.parkinglot.entity.reservation;

import db.parkinglot.entity.Member;
import db.parkinglot.entity.ParkingLot;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ParkingLotReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "parkingLot_id")
    private ParkingLot parkingLot;

    private String date;

    private String enterTime;

    private String exitTime;
}
