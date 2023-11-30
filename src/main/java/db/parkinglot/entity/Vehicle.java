package db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id @GeneratedValue
    private String carId;

    @Column(nullable = false)
    private String brand;

    @ManyToOne(targetEntity = Member.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private String color;

}
