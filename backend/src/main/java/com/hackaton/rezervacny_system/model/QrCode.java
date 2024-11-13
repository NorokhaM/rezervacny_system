package com.hackaton.rezervacny_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String date;

    @Column
    private LocalDateTime time;

    @Lob
    private byte[] qrCode;

    @OneToOne(mappedBy = "qrCode")
    @JsonIgnore
    private Reservation reservation;

    public QrCode(String date, LocalDateTime time, byte[] qrCode) {
        this.date = date;
        this.time = LocalDateTime.now();
        this.qrCode = qrCode;
    }

}
