package edu.itplus.bibliospring.backend.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

class PasswordEncrypterTest {

    @Test
    void hashPassword() {
    }


    @ParameterizedTest
    @CsvSource({
            "kiskutya, 4491e5b4-ee14-45d2-a532-c4a276cd69de, 3F162714CA3FD2FB0FC88C7C5121562E0A194E074B33067EA90E6B488E56702D",
            "alma, 123a10da-1226-4637-b9cb-b28cbbffeb8d, 1382B89A107D9278A7B044EA1AFCCABA5C05BB4F2F41042101305662FFFD52D8",
            "test, bd207845-bdf3-45f7-af8e-b2e2763ddccc, C9834A8F064DF1CA8CB324E120D4192FE34A9858628EF0AF6AF0F4ADA531215B",
            "password,ebddeb7b-32fb-4f51-9743-190bedfc0e7f,BC50CD2F0BC9EE55739E2EC419FF45C6F7E160A64A23136A9A171F9AD7B19CF6",
            "hello,12345678-aaaa-bbbb-cccc-123456789abc,D40B8A34E4785FAFC978D746F1CBDB61C276704A31FB822AA9ACF831A261450D",
            "password123,18b09ff8-9718-49ea-9588-c1a65ff10d8e,7DA449FC9ECCB29FF0312FFF1F88C125B38B2F1EE5C37DD52B52A1EE9593883B",
    })
    void hashPassword_givenValidPasswordAndHash_returnsHashedPassword(String password, String salt, String expectedHash){
        //Arrange
        PasswordEncrypter sut = new PasswordEncrypter();
        // Act
        String hash = sut.hashPassword(password,salt);
        // Assert
        assertThat(hash).isEqualTo(expectedHash);
    }

}