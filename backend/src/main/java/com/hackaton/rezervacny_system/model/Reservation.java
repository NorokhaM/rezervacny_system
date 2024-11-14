package com.hackaton.rezervacny_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String date;

    @Column
    private String time;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "playground_id", referencedColumnName = "id")
    private Playground playground;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qr_code_id", referencedColumnName = "id")
    private QrCode qrCode;
}
