package com.star.wlh.common.dao.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname AbstractMongoGenDao
 * @Description 抽象实现类，默认实现一些与业务无关的功能。
 * @Date 2023/6/9 23:13
 * @Created by wlh
 */
public abstract class AbstractMongoGenDao<T> implements MongoGenDao<T> {
    protected static final String FIELD_ID = "_id";
    private Map<Class<?>, String> entity2Collection = new ConcurrentHashMap<>();
    @Autowired
    @Qualifier("mainMongoTemplate")
    protected MongoTemplate mongoTemplate;

    @Autowired
    @Qualifier("readPreferSecondaryMongoTemplate")
    protected MongoTemplate readPreferSecondaryMongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(T t) {
        mongoTemplate.save(t);
    }

    @Override
    public void insert(T t) {
        mongoTemplate.insert(t);
    }

    @Override
    public void insertMany(List<T> ts) {
        mongoTemplate.insertAll(ts);
    }

    @Override
    public DeleteResult deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(FIELD_ID).is(convert2ObjectIdIfNeeded(id)));
        return mongoTemplate.remove(query, this.getEntityClass(), getCollectionName());
    }

    @Override
    public DeleteResult delete(T t) {
        if (t != null) {
            return this.mongoTemplate.remove(t, getCollectionName());
        }
        return DeleteResult.acknowledged(0);
    }

    @Override
    public boolean isLogicallyDelete() {
        return false;
    }

    @Override
    public long deleteLogically(String id, Date now) {
        return 0;
    }

    @Override
    public long deleteLogically(String id, Date now, long version) {
        return 0;
    }

    @Override
    public T getById(String id) {
        return null;
    }

    @Override
    public List<T> getList(Query query) {
        return null;
    }

    @Override
    public List<T> getList(Query query, boolean perferSecondary) {
        return null;
    }

    @Override
    public T getOne(Query query) {
        return null;
    }

    @Override
    public List<T> getPage(Query query, int start, int size) {
        return null;
    }

    @Override
    public Long getCount(Query query) {
        return null;
    }

    @Override
    public DeleteResult delete(Query query) {
        return null;
    }

    @Override
    public UpdateResult updateFirst(Query query, Update update) {
        return null;
    }

    @Override
    public UpdateResult updateById(String id, Update update) {
        return null;
    }

    @Override
    public UpdateResult updateMulti(Query query, Update update) {
        return null;
    }

    @Override
    public UpdateResult updateInser(Query query, Update update) {
        return null;
    }

    @Override
    public T findAndModify(Query query, Update update, FindAndModifyOptions options) {
        return null;
    }

    @Override
    public Criteria getTenantCriteria() {
        return null;
    }

    @Override
    public Criteria getBaseCriteria(Boolean queryDeleted) {
        return null;
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return null;
    }


    /**
     * 模板方法，由子类实现返回反射对象的类型。
     *
     * @return 业务对象类
     */
    protected abstract Class<T> getEntityClass();

    @Override
    public void createCollection() {

    }

    @Override
    public void dropCollection() {

    }

    @Override
    public void dropIndex(String name) {

    }

    @Override
    public void createIndex(Index index) {

    }

    @Override
    public List<T> shrinkQuery(Query query, String... includedFileds) {
        return null;
    }

    @Override
    public <R> List<R> findDistinct(Query query, String field, Class<R> resultClass) {
        return null;
    }

    private Object convert2ObjectIdIfNeeded(String id) {
        return ObjectId.isValid(id) ? new ObjectId(id) : id;
    }

    /*
     * 返回当前对象类型所对应的集合名。
     */
    protected String getCollectionName() {
        return entity2Collection.computeIfAbsent(getEntityClass(), entityClass -> {
            String collectionName = null;
            Annotation[] annotations = entityClass.getAnnotations();
            for (Annotation anno : annotations) {
                if (anno instanceof org.springframework.data.mongodb.core.mapping.Document) {
                    collectionName = org.springframework.data.mongodb.core.mapping.Document.class.cast(anno).collection();
                    break;
                }
            }
            if (collectionName == null || ObjectUtils.isEmpty(collectionName)) {
                collectionName = StringUtils.uncapitalize(entityClass.getSimpleName());
            }
            return collectionName;
        });
    }

}
