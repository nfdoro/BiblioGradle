package edu.itplus.bibliospring.backend.utils;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

class PasswordEncrypterTest {

    @Test
    void hashPassword() {
    }

    @Test
    void hashPassword_givenValidPasswordAndHash_returnsHashedPassword(){
        //Arrange
        PasswordEncrypter sut = new PasswordEncrypter();
        String password ="abcde";
        String salt = UUID.randomUUID().toString();

        //act
        String hash = sut.hashPassword(password,salt);

        // Assert
        String expectedHash = "1234";
        assertThat(hash).isEqualTo(expectedHash);
    }

}