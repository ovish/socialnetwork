package com.solvd.socialnetwork;

import com.solvd.socialnetwork.impl.UserDAO;
import com.solvd.socialnetwork.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        User user = new User(1, "user1", "John", "Smith", "john@gmail.com", "12345", "picture.url", LocalDate.of(1991, 5, 20), LocalDateTime.now());
        userDAO.save(user);





    }
}
