package com.example.rethinkdb.rethinkdb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAsync
public class RethinkdbApplication implements CommandLineRunner {

	// First test.
	//public static final RethinkDB r = RethinkDB.r;

	public static void main(String[] args) {
		SpringApplication.run(RethinkdbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// First test.
//		Connection conn = r.connection().hostname("localhost").port(28015).connect();
//
//		r.db("test").tableCreate("tv_shows").run(conn);
//		r.table("tv_shows").insert(r.hashMap("name", "Star Trek TNG")).run(conn);
//
//		for (Object doc :  r.table("tv_shows").changes().run(conn)) {
//			System.out.println(doc);
//		}
	}

}
