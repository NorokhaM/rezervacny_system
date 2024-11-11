package com.hackaton.rezervacny_system.service;

import com.hackaton.rezervacny_system.model.Reservation;
import com.hackaton.rezervacny_system.repository.MyUserRepository;
import com.hackaton.rezervacny_system.repository.PlaygroundRepository;
import com.hackaton.rezervacny_system.repository.ReservationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MyUserRepository myUserRepository;
    private final PlaygroundRepository playgroundRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, MyUserRepository myUserRepository, PlaygroundRepository playgroundRepository) {
        this.reservationRepository = reservationRepository;
        this.myUserRepository = myUserRepository;
        this.playgroundRepository = playgroundRepository;
    }

    public Reservation addReservation(Reservation reservation, Long userId, Long playgroundId) {
        reservation.setUser(
                myUserRepository.findById(userId).orElseThrow()
        );
        reservation.setPlayground(
                playgroundRepository.findById(playgroundId).orElseThrow()
        );
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public boolean deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        return true;
    }

    public List<String> getTimeReservationByPlaygroundIdAndDate(Long playgroundId, String date) {
        return reservationRepository.findTimeByPlaygroundIdAndDate(playgroundId, date);
    }

    public boolean reservationExists(String date, String time, Long playgroundId) {
        return reservationRepository
                .findTimeByPlaygroundIdAndDate(playgroundId, date)
                .contains(time);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void deletePastReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for (Reservation reservation : reservations) {
            LocalDateTime reservationDateTime = LocalDateTime.parse(reservation.getDate() + " " + reservation.getTime(), formatter);
            if (reservationDateTime.isBefore(now)) {
                reservationRepository.delete(reservation);
            }
        }
    }
}
