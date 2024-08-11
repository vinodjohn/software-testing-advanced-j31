package org.sda.j31.softwaretestingadvanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sda.j31.softwaretestingadvanced.exceptions.ServiceUnavailableException;
import org.sda.j31.softwaretestingadvanced.models.User;
import org.sda.j31.softwaretestingadvanced.models.UserType;
import org.sda.j31.softwaretestingadvanced.services.UserService;
import org.sda.j31.softwaretestingadvanced.services.implementations.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Tests for UserService
 *
 * @author Vinod John
 * @Date 11.08.2024
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void whenGetUserFromRemoteCalled_shouldReturnUser() throws ServiceUnavailableException {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        Mockito.verifyNoInteractions(restTemplate);

        Mockito.when(restTemplate.getForEntity("https://www.google.com/user", User.class))
                .thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        User resultUser = userService.getUserFromRemoteServer();

        Assertions.assertEquals(user.getFirstName(), resultUser.getFirstName());
        Assertions.assertEquals(user.getLastName(), resultUser.getLastName());
        Assertions.assertEquals(user.getPassword(), resultUser.getPassword());
        Assertions.assertEquals(user.getUserType(), resultUser.getUserType());
    }

    @Test
    public void whenGetUserFromRemoteCalled_shouldThrowException() {
        Mockito.when(restTemplate.getForEntity("https://www.google.com/user", User.class))
                .thenThrow(HttpServerErrorException.class);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(ServiceUnavailableException.class)
                .isThrownBy(() -> userService.getUserFromRemoteServer())
                .withNoCause()
                .withMessageContaining("unavailable!");
    }
}
