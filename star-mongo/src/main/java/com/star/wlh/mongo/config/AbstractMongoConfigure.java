package com.star.wlh.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import lombok.Data;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.ArrayList;
import java.util.List;
@Data
public abstract class AbstractMongoConfigure {

    private String host;
    private int port;
    private String username;
    private String password;
    private String database;

    public MongoDatabaseFactory mongoDatabaseFactory() {
        String url = "mongodb://"+username+":"+password+"@"+host+":"+port+"/"+database+"?authSource=admin&authMechanism=SCRAM-SHA-1";
        ConnectionString connectionString = new ConnectionString(url);
        return new SimpleMongoClientDatabaseFactory(connectionString);
    }

    abstract public MongoTemplate getMongoTemplate();
}