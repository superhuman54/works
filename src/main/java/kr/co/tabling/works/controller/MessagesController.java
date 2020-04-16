package kr.co.tabling.works.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {


    @GetMapping("/")
    public String message() {
        return null;
    }
}
