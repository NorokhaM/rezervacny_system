package com.hackaton.rezervacny_system.repository;

import com.hackaton.rezervacny_system.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
}
