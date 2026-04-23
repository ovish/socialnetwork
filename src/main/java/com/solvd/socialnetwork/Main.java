package com.solvd.socialnetwork;

import com.solvd.socialnetwork.model.User;
import com.solvd.socialnetwork.xml.JaxbUtil;
import com.solvd.socialnetwork.xml.SocialNetworkSaxHandler;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SocialNetworkSaxHandler handler = new SocialNetworkSaxHandler();

            try (InputStream is = Main.class.getClassLoader().getResourceAsStream("social-data.xml")) {
                parser.parse(is, handler);
            }

            LOGGER.info("Users: {}", handler.getUsers().size());
            LOGGER.info("Posts: {}", handler.getPosts().size());
            LOGGER.info("Hashtags: {}", handler.getHashtags().size());
            LOGGER.info("PostHashtags: {}", handler.getPostHashtags().size());
            LOGGER.info("Friendships: {}", handler.getFriendships().size());
            LOGGER.info("Follows: {}", handler.getFollows().size());
        } catch (Exception e) {
            LOGGER.error("Failed to parse social-data.xml", e);
        }


        User user = new User(
                1L,
                "olga",
                "Olga",
                "Vishenkova",
                "ovishenkova@solvd.com",
                "secret",
                "avatar.png",
                LocalDate.of(1995, 6, 12),
                LocalDateTime.now()
        );

        File xmlFile = new File("src/main/resources/users.xml");

        try {
            JaxbUtil.marshal(user, xmlFile);
            LOGGER.info("Saved user to {}", xmlFile.getAbsolutePath());

            User loaded = JaxbUtil.unmarshal(User.class, xmlFile);
            LOGGER.info("Username: {}", loaded.getUsername());
            LOGGER.info("Email: {}", loaded.getEmail());
            LOGGER.info("Id: {}", loaded.getId());
        } catch (JAXBException e) {
            LOGGER.error("JAXB operation failed", e);
        }
    }
}
