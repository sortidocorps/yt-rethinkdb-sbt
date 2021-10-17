package com.example.rethinkdb.rethinkdb.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class RethinkDbConfiguration {

    @Autowired
    private Environment env;

    public static String DBHOST = "localhost";
    public static Integer DBPORT = 28015;

    @PostConstruct
    public void init() {
        this.DBHOST = this.env.getProperty("rethinkdb.dbhost");
        this.DBPORT = Integer.parseInt(this.env.getProperty("rethinkdb.dbport"));
    }

    @Bean
    public RethinkDbConnectionFactory connectionFactory() {
        return new RethinkDbConnectionFactory(DBHOST, DBPORT);
    }

    @Bean
    DbInitializer dbInitializer() {
        return new DbInitializer();
    }
}
