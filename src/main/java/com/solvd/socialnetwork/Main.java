package com.solvd.socialnetwork;

import com.solvd.socialnetwork.impl.UserDAO;
import com.solvd.socialnetwork.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();


        User user2 = new User(2L, "user2", "Ann", "Li", "ann@gmail.com", "12345", "picture2.url", LocalDate.of(1990, 2, 11), LocalDateTime.now());
        userDAO.save(user2);





    }
}
