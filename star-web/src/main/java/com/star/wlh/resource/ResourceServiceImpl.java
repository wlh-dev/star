package com.star.wlh.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @Classname ResourceServiceImpl
 * @Description TODO
 * @Date 2023/6/6 19:52
 * @Created by wlh
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean save(ResourceEntity resource) {

        return false;
    }
}
