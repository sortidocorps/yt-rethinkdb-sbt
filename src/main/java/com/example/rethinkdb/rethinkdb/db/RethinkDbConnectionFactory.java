package com.example.rethinkdb.rethinkdb.db;


import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class RethinkDbConnectionFactory {
    private String host;
    private Integer port;

    public RethinkDbConnectionFactory(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Connection createConnection() {
        return RethinkDB.r.connection().hostname(host).port(port).connect();
    }
}
