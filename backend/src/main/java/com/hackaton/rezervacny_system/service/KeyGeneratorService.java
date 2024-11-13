package com.hackaton.rezervacny_system.service;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Arrays;

@Service
public class KeyGeneratorService {

    public String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();
        return new String(Hex.encode(secretKey.getEncoded()));
    }
}
