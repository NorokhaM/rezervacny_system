package com.hackaton.rezervacny_system.controller;

import com.hackaton.rezervacny_system.model.Reservation;
import com.hackaton.rezervacny_system.service.KeyGeneratorService;
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
    public ResponseEntity<?> addReservation(@PathVariable Long userId, @PathVariable Long playgroundId, @RequestBody Reservation reservation){
        String date = reservation.getDate();
        String time = reservation.getTime();
        if (reservationService.reservationExists(date, time, playgroundId)){
            return ResponseEntity.badRequest().body("Reservation already exists");
        }
        return ResponseEntity.ok(reservationService.addReservation(reservation, userId, playgroundId));
    }


    @GetMapping("/get/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @GetMapping("/get/playground/{playgroundId}")
    public ResponseEntity<List<String>> getTimeReservationByPlaygroundIdAndDate(@PathVariable Long playgroundId, @RequestParam String date){
        return ResponseEntity.ok(reservationService.getTimeReservationByPlaygroundIdAndDate(playgroundId, date));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }

}
