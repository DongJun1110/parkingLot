package db.parkinglot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sort;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int totalSpace;

    @ManyToOne(targetEntity = Member.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    private int leftSpace;

    private String startTime;

    private String endTime;

    private String fee;

    private int feePerHour;

    private int dayFee;

    private int monthFee;

    private String significant;

    private String company;

    private String contactNumber;

}
