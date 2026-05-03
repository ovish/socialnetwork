package com.solvd.socialnetwork;

import com.solvd.socialnetwork.model.User;
import com.solvd.socialnetwork.util.JaxbUtil;
import com.solvd.socialnetwork.util.JsonParser;
import com.solvd.socialnetwork.util.SocialNetworkSaxHandler;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SocialNetworkSaxHandler handler = new SocialNetworkSaxHandler();

            try (InputStream is = Main.class.getClassLoader().getResourceAsStream("social-data.xml")) {
                parser.parse(is, handler);
            }

            logger.info("Users: {}", handler.getUsers().size());
            logger.info("Posts: {}", handler.getPosts().size());
            logger.info("Hashtags: {}", handler.getHashtags().size());
            logger.info("PostHashtags: {}", handler.getPostHashtags().size());
            logger.info("Friendships: {}", handler.getFriendships().size());
            logger.info("Follows: {}", handler.getFollows().size());
        } catch (Exception e) {
            logger.error("Failed to parse social-data.xml", e);
        }


        User user = new User(
                1L,
                "olga",
                "Olga",
                "Smith",
                "ovi12@yahoo.com",
                "secret",
                "avatar.png",
                LocalDate.of(1995, 6, 12),
                LocalDateTime.now()
        );

        File xmlFile = new File("src/main/resources/users.xml");

        try {
            JaxbUtil.marshal(user, xmlFile);
            logger.info("Saved user to {}", xmlFile.getAbsolutePath());

            User loaded = JaxbUtil.unmarshal(User.class, xmlFile);
            logger.info("Username: {}", loaded.getUsername());
            logger.info("Email: {}", loaded.getEmail());
            logger.info("Id: {}", loaded.getId());
        } catch (JAXBException e) {
            logger.error("JAXB operation failed", e);
        }



        String json = JsonParser.toJsonString(user);
        logger.info("JSON string");

        User parsedUser = JsonParser.fromJson(json, User.class);
        logger.info("Parsed user");


        File file = new File("src/main/resources/user.json");
        JsonParser.toJson(user, file);
        logger.info("Saved object to file", file.getAbsolutePath());

        User fromFile = JsonParser.fromJson(file, User.class);
        logger.info("from Json");

        User user2 = new User(
                2L,
                "bob42",
                "Bob",
                "Jones",
                "bob@example.com",
                "pass456",
                "bob.png",
                LocalDate.of(1995, 3, 10),
                LocalDateTime.of(2023, 6, 20, 8, 0, 0)
        );

        File listFile = new File("src/main/resources/users.json");
        JsonParser.toJson(List.of(user, user2), listFile);

        List<User> users = JsonParser.fromJsonList(listFile, User.class);
        logger.info("Total users: {}", users.size());
        users.forEach(u -> logger.info(" - {} | {} | registered: {}",
                u.getUsername(),
                u.getEmail(),
                u.getRegisterDate()
        ));

    }

}
