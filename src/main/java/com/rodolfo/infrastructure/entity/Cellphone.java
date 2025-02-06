package com.rodolfo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "cellphone")
public class Cellphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "areaCode", length = 3)
    private String areaCode;

    @Column(name = "number", length = 9)
    private String number;

    @Column(name = "person_id")
    private Long person_id;
}
