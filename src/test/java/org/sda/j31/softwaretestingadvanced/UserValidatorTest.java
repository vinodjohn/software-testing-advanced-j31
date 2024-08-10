package org.sda.j31.softwaretestingadvanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sda.j31.softwaretestingadvanced.exceptions.UserValidationException;
import org.sda.j31.softwaretestingadvanced.models.User;
import org.sda.j31.softwaretestingadvanced.models.UserType;
import org.sda.j31.softwaretestingadvanced.utils.UserValidator;

/**
 * Testing Exceptions using JUnit5, AssertJ and JUnit4
 *
 * @author Vinod John
 * @Date 10.08.2024
 */
public class UserValidatorTest {
    //JUnit5 examples
    @Test
    public void givenUser_whenIsAdminCalled_shouldExpectException() {
        try {
            User user = new User();
            user.setFirstName("Tony");
            user.setLastName("Stark");
            user.setPassword("123456");
            user.setUserType(UserType.STANDARD);

            UserValidator userValidator = new UserValidator();
            userValidator.isAdmin(user);
        } catch (UserValidationException e) {
            String expectedMessage = "User validation failed for user: Tony Stark, Error: User admin check failed!";
            Assertions.assertEquals(expectedMessage, e.getLocalizedMessage());
        }
    }

    @Test
    public void givenUser_whenIsAdminCalledFunctionally_shouldExpectException() {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        UserValidationException userValidationException = Assertions.assertThrows(UserValidationException.class, () -> {
            UserValidator userValidator = new UserValidator();
            userValidator.isAdmin(user);
        });

        String expectedMessage = "User validation failed for user: Tony Stark, Error: User admin check failed!";
        Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());
    }

    //AssertJ Examples
    @Test
    public void givenUserWithoutUserType_whenIsAdminCalled_shouldExpectNPE() {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new UserValidator().isAdmin(user))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessageContaining("no");
    }

    @Test
    public void givenUser_whenIsAdminCalled_shouldExpectExceptionAssert() {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(UserValidationException.class)
                .isThrownBy(() -> new UserValidator().isAdmin(user))
                .withNoCause()
                .withMessageContaining("User validation failed for user: Tony Stark, Error: User admin check failed!");
    }

    @Test
    public void givenUserWithoutUserType_whenIsAdminCalled_shouldExpectException() {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");

        Throwable throwable = org.assertj.core.api.Assertions.catchThrowable(() -> new UserValidator().isAdmin(user));
        Assertions.assertEquals("User Tony Stark has no UserType", throwable.getMessage());
    }

    @Test
    public void givenUser_whenIsAdminCalled_shouldExpectUserValidationException() {
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setPassword("123456");
        user.setUserType(UserType.STANDARD);

        UserValidationException userValidationException = org.assertj.core.api.Assertions.catchThrowableOfType(() -> new UserValidator().isAdmin(user), UserValidationException.class);

        String expectedMessage = "User validation failed for user: Tony Stark, Error: User admin check failed!";
        Assertions.assertEquals(expectedMessage, userValidationException.getLocalizedMessage());
    }

    //JUnit4 example
//    @Test(expect = UserValidationException.class)
//    public void givenUser_whenIsAdminCalled_shouldExpectException_Junit4() {
//        User user = new User();
//        user.setFirstName("Tony");
//        user.setLastName("Stark");
//        user.setPassword("123456");
//        user.setUserType(UserType.STANDARD);
//
//        new UserValidator().isAdmin(user);
//    }
}
