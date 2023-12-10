package com.humber.j2ee.week5.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaunchYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int launchYearId;

    @Min(2000)
    @Max(2024)
    private int launchYear;
}

