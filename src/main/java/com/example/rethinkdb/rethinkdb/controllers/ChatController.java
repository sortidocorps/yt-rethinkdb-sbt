package com.example.rethinkdb.rethinkdb.controllers;


import com.example.rethinkdb.rethinkdb.db.RethinkDbConnectionFactory;
import com.example.rethinkdb.rethinkdb.models.ChatMessage;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    protected final Logger log = LoggerFactory.getLogger(ChatController.class);
    private static final RethinkDB r = RethinkDB.r;

    private RethinkDbConnectionFactory connectionFactory;

    public ChatController(RethinkDbConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ChatMessage postMessage(@RequestBody ChatMessage chatMessage) {
        chatMessage.setTime(OffsetDateTime.now());
        Result<Object> run = r.db("chat").table("messages").insert(chatMessage)
                .run(connectionFactory.createConnection());

        log.info("Insert {}", run);
        return chatMessage;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChatMessage> getMessages() {

        return (List<ChatMessage>) r.db("chat").table("messages")
                .orderBy().optArg("index", r.desc("time"))
                .limit(20)
                .orderBy("time")
                .run(connectionFactory.createConnection(), ChatMessage.class);

    }
}
