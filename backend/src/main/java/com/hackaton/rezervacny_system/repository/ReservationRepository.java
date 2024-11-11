package com.hackaton.rezervacny_system.repository;

import com.hackaton.rezervacny_system.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);

    @Query("SELECT r.time FROM Reservation r WHERE r.playground.id = :playgroundId AND r.date = :date")
    List<String> findTimeByPlaygroundIdAndDate(Long playgroundId, String date);
}
