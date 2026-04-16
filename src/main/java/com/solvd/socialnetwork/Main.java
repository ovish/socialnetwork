package com.solvd.socialnetwork;

import com.solvd.socialnetwork.xml.SocialNetworkSaxHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

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
    }
}
