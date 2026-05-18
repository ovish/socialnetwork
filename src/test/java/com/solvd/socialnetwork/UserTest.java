package com.solvd.socialnetwork;

import com.solvd.socialnetwork.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserTest {

    private User user;

    @BeforeClass
    public void setUp() {
        user = new User(5L, "tom99", "Tom", "Smith",
            "tom@example.com", "password123", "tom.png",
            LocalDate.of(1999, 5, 20), LocalDateTime.now());
}

    @Test
    public void testCreateUser() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(user);
        softAssert.assertEquals(user.getUsername(), "tom99");
        softAssert.assertEquals(user.getEmail(), "tom@example.com");
        softAssert.assertAll();
    }

    @Test
    public void testUsernameNotEmpty() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(user.getUsername());
        softAssert.assertFalse(user.getUsername().isEmpty(), "Username should not be empty");
        softAssert.assertAll();

    }

    @Test
    public void testEmailContainsAtSign() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(user.getEmail().contains("@"), "Email should contain @");
        softAssert.assertAll();

    }

    @Test
    public void testPasswordLength() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(user.getPassword().length() >= 6, "Password should be at least 6 characters");
        softAssert.assertAll();
    }

    @Test
    public void testUpdateEmail() {
        SoftAssert softAssert = new SoftAssert();
        String newEmail = "newtom@example.com";
        user.setEmail(newEmail);
        softAssert.assertEquals(user.getEmail(), newEmail, "Email should be updated");
        softAssert.assertAll();

    }


}
