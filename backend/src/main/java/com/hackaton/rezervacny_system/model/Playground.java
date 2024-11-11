package com.hackaton.rezervacny_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "playgrounds")
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "playground")
    @JsonIgnore
    private List<Reservation> reservations;

}
