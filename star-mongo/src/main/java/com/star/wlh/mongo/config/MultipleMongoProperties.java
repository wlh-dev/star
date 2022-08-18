package com.star.wlh.mongo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
 
@ConfigurationProperties(prefix = "mongodb")
@Configuration
public class MultipleMongoProperties {
    private MongoProperties cmdbProperties = new MongoProperties();
    private MongoProperties pacific = new MongoProperties();

    public MongoProperties getCmdbProperties() {
        return cmdbProperties;
    }

    public void setCmdbProperties(MongoProperties cmdbProperties) {
        this.cmdbProperties = cmdbProperties;
    }

    public MongoProperties getPacific() {
        return pacific;
    }

    public void setPacific(MongoProperties pacific) {
        this.pacific = pacific;
    }
}