package org.sda.j31.softwaretestingadvanced.services;

import org.sda.j31.softwaretestingadvanced.exceptions.ServiceUnavailableException;
import org.sda.j31.softwaretestingadvanced.models.User;

/**
 * Service to handle user related operations
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
public interface UserService {
    /**
     * To fetch user from a remote server
     *
     * @return User
     * @throws ServiceUnavailableException exception
     */
    User getUserFromRemoteServer() throws ServiceUnavailableException;
}
