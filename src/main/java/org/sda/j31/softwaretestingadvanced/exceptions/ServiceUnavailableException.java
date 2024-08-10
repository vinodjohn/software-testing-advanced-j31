package org.sda.j31.softwaretestingadvanced.exceptions;

/**
 * Exception to handle server unavailability
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
public class ServiceUnavailableException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
