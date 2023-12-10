package com.humber.j2ee.week5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "VINNUM")
    private String id;

    @Embedded
    @NotNull
    private Vin vin;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private  Make make;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private CarModel model;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private LaunchYear launchYear;
    private String transmission;
    private String imageUrl;
}
