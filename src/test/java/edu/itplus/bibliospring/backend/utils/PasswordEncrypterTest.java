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
        String salt =  "6a67ed2b-d547-40dd-a341-b046838fd906";

        // Act
        String hash = sut.hashPassword(password,salt);

        // Assert
        String expectedHash = "DD63FBA9758305932E52E665C24D204E716EB5D3CA6FD783C01E1A013F65C2E0";
        assertThat(hash).isEqualTo(expectedHash);
    }

}