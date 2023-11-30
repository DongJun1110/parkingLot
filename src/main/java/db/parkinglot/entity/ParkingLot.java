package db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private String totalSpace;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    private int feePerHour;

    private int dayFee;

    private int monthFee;

    private String significant;

    @Column(nullable = false)
    private String company;

    private String contactNumber;

}
