package com.example.rethinkdb.rethinkdb.db;

import com.example.rethinkdb.rethinkdb.services.ChatChangesListener;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;



public class DbInitializer implements InitializingBean {
    @Autowired
    private RethinkDbConnectionFactory connectionFactory;

    @Autowired
    private ChatChangesListener chatChangesListener;

    private static final RethinkDB r = RethinkDB.r;

    @Override
    public void afterPropertiesSet() throws Exception {
        createDb();
        chatChangesListener.pushChangesToWebSocket();
    }

    private void createDb() {
        Connection connection = connectionFactory.createConnection();
        Result<Object> dbList = r.dbList().run(connection);
        if (dbList.toList().size() <= 0) {
            r.dbCreate("chat").run(connection);
        }

        Result<Object> tables = r.db("chat").tableList().run(connection);
        if (tables.toList().size() <= 0) {
            r.db("chat").tableCreate("messages").run(connection);
            r.db("chat").table("messages").indexCreate("time").run(connection);
        }
    }
}
