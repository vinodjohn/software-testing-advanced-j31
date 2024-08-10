package org.sda.j31.softwaretestingadvanced.utils;

import org.sda.j31.softwaretestingadvanced.exceptions.UserValidationException;
import org.sda.j31.softwaretestingadvanced.models.User;
import org.sda.j31.softwaretestingadvanced.models.UserType;

/**
 * Validation utils for user
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
public class UserValidator {
    public boolean isAdmin(User user) throws UserValidationException {
        String fullName = user.getFirstName() + " " + user.getLastName();

        if (user.getUserType() == null) {
            throw new NullPointerException(String.format("User %s has no UserType", fullName));
        }

        if (UserType.ADMIN.equals(user.getUserType())) {
            return true;
        } else {
            throw new UserValidationException(fullName, "User admin check failed!");
        }
    }

    public String encodePassword(String password) {
        int midIndex = password.length() / 2;
        return password.substring(0, midIndex) + "#sda_java@" + password.substring(midIndex);
    }
}
