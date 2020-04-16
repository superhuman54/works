package kr.co.tabling.works.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class WelcomeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/api/hello")
    public String hello() {
        String message = "hello the time at server is now " + new Date() + "\n";
        logger.debug(message);
        return message;
    }

    @GetMapping("/api/goodbye")
    public String goodbye() {
        return "goodbye";
    }

    @DeleteMapping("/messages/{id}")
    public String delete(@PathVariable Integer id) {
        return "deleted";
    }
}
