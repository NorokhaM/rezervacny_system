package com.hackaton.rezervacny_system.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.hackaton.rezervacny_system.model.QrCode;
import com.hackaton.rezervacny_system.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;

    @Autowired
    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public QrCode saveQrCode(String key) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix=qrCodeWriter.encode(key, BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage bufferedImage= MatrixToImageWriter.toBufferedImage(matrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageInByte = baos.toByteArray();
        QrCode qrCode = new QrCode(key, LocalDateTime.now(), imageInByte);
        qrCodeRepository.save(qrCode);
        return qrCode;
    }

    public String readQRCode(byte[] qrCodeImage) throws IOException, NotFoundException {
        InputStream inputStream = new ByteArrayInputStream(qrCodeImage);
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    public byte[] getQrCodeImage(Long id) {
        return qrCodeRepository
                .findById(id)
                .get()
                .getQrCode();
    }


}
