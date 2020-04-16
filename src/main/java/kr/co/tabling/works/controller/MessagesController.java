package kr.co.tabling.works.controller;

import kr.co.tabling.works.persist.entity.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {


    @PostMapping()
    public String post(Message body) {
        return null;
    }
}
