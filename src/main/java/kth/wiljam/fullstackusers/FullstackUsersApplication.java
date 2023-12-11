package kth.wiljam.fullstackusers;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class FullstackUsersApplication {

    private static final Logger logger = LoggerFactory.getLogger(FullstackUsersApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FullstackUsersApplication.class, args);
        logger.info("Logging test, application started");
    }

}
