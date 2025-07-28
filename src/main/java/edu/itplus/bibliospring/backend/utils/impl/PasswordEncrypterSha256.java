package edu.itplus.bibliospring.backend.utils.impl;

import edu.itplus.bibliospring.backend.utils.PasswordEncryptor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@Profile("JDBC")
public class PasswordEncrypterSha256 implements PasswordEncryptor {
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
