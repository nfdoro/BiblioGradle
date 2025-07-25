package edu.itplus.bibliospring.backend.utils;

public interface PasswordEncryptor {
    String hashPassword(String password, String salt);
}
