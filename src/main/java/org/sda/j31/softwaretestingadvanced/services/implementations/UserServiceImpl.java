package org.sda.j31.softwaretestingadvanced.services.implementations;

import org.sda.j31.softwaretestingadvanced.exceptions.ServiceUnavailableException;
import org.sda.j31.softwaretestingadvanced.models.User;
import org.sda.j31.softwaretestingadvanced.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *Implementation of User Service
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User getUserFromRemoteServer() throws ServiceUnavailableException {
        try {
            ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("https://www.google.com/user", User.class);
            return userResponseEntity.getBody();
        } catch (HttpServerErrorException httpServerErrorException) {
            throw new ServiceUnavailableException("Server unavailable!");
        }
    }
}
