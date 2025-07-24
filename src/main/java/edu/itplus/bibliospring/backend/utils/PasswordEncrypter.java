package edu.itplus.bibliospring.backend.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncrypter {
    public String hashPassword(String password, String salt){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] input = (password + salt).getBytes();

            messageDigest.reset();
            messageDigest.update(input);
            byte[] output = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte out : output) {
                stringBuffer.append(String.format("%02X",out));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
