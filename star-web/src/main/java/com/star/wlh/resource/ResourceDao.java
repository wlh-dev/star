package com.star.wlh.resource;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.star.wlh.common.dao.mongo.MongoGenDao;
import org.bson.Document;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * @Classname ResourceDao
 * @Description 类型DAO接口。 扩展一些方法
 * @Date 2023/6/9 23:12
 * @Created by wlh
 */
public interface ResourceDao extends MongoGenDao<ResourceEntity> {

}
