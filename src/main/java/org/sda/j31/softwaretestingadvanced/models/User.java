package org.sda.j31.softwaretestingadvanced.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

/**
 * User model
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
@Data
public class User {
    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
