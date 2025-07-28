package edu.itplus.bibliospring.backend.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;

import edu.itplus.bibliospring.backend.utils.PasswordEncryptor;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
class LoginServiceImplTest {

    private LoginServiceImpl serviceUnderTest;

    private UserDAO testUserDao;

    private PasswordEncryptor testPasswordEncrypter;

    private User nonDbUser;

    private User dbUser;
    @BeforeEach
    void setUp() {
        // Initialize the service under test
        serviceUnderTest = new LoginServiceImpl();

        nonDbUser = new User();
        nonDbUser.setPassword(TestPasswordEncryptor.password);
        nonDbUser.setUsername("Pistike");
        nonDbUser.setUuid(TestPasswordEncryptor.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setPassword(TestPasswordEncryptor.hashedPassword);
        dbUser.setUsername("Pistike");
        dbUser.setUuid(TestPasswordEncryptor.salt);
        dbUser.setId(1L);

        testUserDao = mock(UserDAO.class);
        testPasswordEncrypter = mock(PasswordEncryptor.class);
        when(testPasswordEncrypter.hashPassword(nonDbUser.getPassword(), nonDbUser.getUuid()))
                .thenReturn(dbUser.getPassword());

        ReflectionTestUtils.setField(serviceUnderTest, "userDAO", testUserDao);
        when(testUserDao.findByUsername(nonDbUser.getUsername())).thenReturn(dbUser);

        ReflectionTestUtils.setField(serviceUnderTest, "passwordEncrypter", testPasswordEncrypter);

        // Mock dependencies if necessary, e.g., UserDAO and PasswordEncrypter
        // This can be done using a mocking framework like Mockito
        // For example:
        // userDAO = mock(UserDAO.class);


    }

    @Test
    void login() {
        // Arrange
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the login method with the mock User object
        boolean loginStatus = serviceUnderTest.login(nonDbUser);

        // Assert
        // Verify that the UserDAO's findByUsername method was called with the correct username
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
        assertThat(loginStatus).isTrue();
        verify(testUserDao, times(1)).findByUsername(nonDbUser.getUsername());

        //assertThat(testUserDao.getLastSearchedUsernName()).isEqualTo(TestUserDAO.nonDbUser.getUsername());
    }

    @Test
    void register() {
        // Arrange
        User userToRegister = new User();
        userToRegister.setPassword(TestPasswordEncryptor.password);
        userToRegister.setUsername("NewUser");
        userToRegister.setUuid(TestPasswordEncryptor.salt);

        when(testPasswordEncrypter.hashPassword(userToRegister.getPassword(), userToRegister.getUuid()))
                .thenReturn(TestPasswordEncryptor.hashedPassword);

        // Act
        serviceUnderTest.register(userToRegister);

        // Assert
        verify(testPasswordEncrypter, times(1)).hashPassword(TestPasswordEncryptor.password, TestPasswordEncryptor.salt);
        verify(testUserDao, times(1)).create(argThat(user ->
                user.getPassword().equals(TestPasswordEncryptor.hashedPassword) &&
                        user.getUsername().equals("NewUser")
        ));   }
}