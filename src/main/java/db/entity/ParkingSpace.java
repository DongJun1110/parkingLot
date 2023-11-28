package db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = ParkingLot.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "parkingLot_id")
    private ParkingLot parkingLot;


}
