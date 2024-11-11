package com.hackaton.rezervacny_system.controller;

import com.hackaton.rezervacny_system.model.Reservation;
import com.hackaton.rezervacny_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add/{userId}/{playgroundId}")
    public ResponseEntity<Reservation> addReservation(@PathVariable Long userId, @PathVariable Long playgroundId, @RequestBody Reservation reservation){
        return ResponseEntity.ok(reservationService.addReservation(reservation, userId, playgroundId));
    }


    @GetMapping("/get")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

}
