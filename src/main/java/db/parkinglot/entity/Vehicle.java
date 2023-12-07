package db.parkinglot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vehicle {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String number;

    @ManyToOne(targetEntity = Member.class)
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private String color;

    private Date createdAt;

}
