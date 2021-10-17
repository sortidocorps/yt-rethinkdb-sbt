package com.example.rethinkdb.rethinkdb.services;

import com.example.rethinkdb.rethinkdb.db.RethinkDbConnectionFactory;
import com.example.rethinkdb.rethinkdb.models.ChatMessage;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class ChatChangesListener {
    protected final Logger log = LoggerFactory.getLogger(ChatChangesListener.class);

    private static final RethinkDB r = RethinkDB.r;

    private RethinkDbConnectionFactory connectionFactory;

    private SimpMessagingTemplate webSocket;

    public ChatChangesListener(RethinkDbConnectionFactory connectionFactory, SimpMessagingTemplate webSocket) {
        this.connectionFactory = connectionFactory;
        this.webSocket = webSocket;
    }

    @Async
    public void pushChangesToWebSocket() {
        Result<ChatMessage> cursor = r.db("chat").table("messages").changes()
                .getField("new_val")
                .run(connectionFactory.createConnection(), ChatMessage.class);

        while (cursor.hasNext()) {
            ChatMessage chatMessage = cursor.next();
            log.info("New message: {}", chatMessage.message);
            webSocket.convertAndSend("/topic/messages", chatMessage);
        }
    }

}
