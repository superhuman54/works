package kr.co.tabling.works.controller;

import kr.co.tabling.works.persist.entity.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessagesController {


    @PostMapping()
    public String post(Message body) {
        return null;
    }

    @PutMapping("/{id}")
    public String put(Message body) {
        return null;
    }
}
