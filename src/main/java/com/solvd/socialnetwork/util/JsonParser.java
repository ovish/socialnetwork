package com.solvd.socialnetwork.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class JsonParser {

    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static <T> T fromJson(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (Exception e) {
            logger.error("Failed to read JSON from file: " + e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("Failed to parse JSON string", e);
            return null;
        }
    }

    public static <T> List<T> fromJsonList(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            logger.error("Failed to read JSON list", e);
            return null;
        }
    }

    public static void toJson(Object object, File file) {
        try {
            objectMapper.writeValue(file, object);
        } catch (Exception e) {
            logger.error("Failed to write JSON to file", e);
        }
    }

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Failed to convert object to JSON string", e);
            return null;
        }
    }
}
