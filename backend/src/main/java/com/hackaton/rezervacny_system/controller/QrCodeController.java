package com.hackaton.rezervacny_system.controller;

import com.google.zxing.NotFoundException;
import com.hackaton.rezervacny_system.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/qrCode")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQrCodeImage(@PathVariable Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.getQrCodeImage(id));
    }

    @PostMapping("/compare/{id}")
    public ResponseEntity<Boolean> compareQrCodes(@RequestParam String data, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(qrCodeService.compareQrCodes(data, id));
        } catch (IOException | NotFoundException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
