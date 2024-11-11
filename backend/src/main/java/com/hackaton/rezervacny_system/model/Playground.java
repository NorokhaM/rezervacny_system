package com.hackaton.rezervacny_system.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "playgrounds")
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToOne(mappedBy = "playground")
    private Reservation reservation;

}
