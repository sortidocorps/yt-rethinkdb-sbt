package com.example.rethinkdb.rethinkdb.controllers;

import com.example.rethinkdb.rethinkdb.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MsgController {

    @MessageMapping("/msg")
    @SendTo("/topic/messages")
    public ChatMessage greeting(ChatMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return message;
    }
}
