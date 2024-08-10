package org.sda.j31.softwaretestingadvanced.exceptions;


/**
 * Exception to handle user validation
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
public class UserValidationException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserValidationException(String name, String message) {
        super(String.format("User validation failed for user: %s, Error: %s", name, message));
    }

}
