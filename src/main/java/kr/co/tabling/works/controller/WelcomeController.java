package kr.co.tabling.works.controller;

import kr.co.tabling.works.service.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class WelcomeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessagesService service;

    @GetMapping("/api/hello")
    public String hello() {
        String message = "hello the time at server is now " + new Date() + "\n";
        logger.debug(message);
        return message;
    }

    @GetMapping("/api/goodbye")
    public String goodbye() {
        return "goodbye baby";
    }

    @PostMapping("/")
    public String post(String body) {
        return body;
    }

    @DeleteMapping("/messages/{id}")
    public String delete(@PathVariable Integer id) {
        return "deleted";
    }

    @PutMapping("/messages/{id}")
    public String put(@PathVariable Integer id) {
        return Integer.toString(id);
    }

}
