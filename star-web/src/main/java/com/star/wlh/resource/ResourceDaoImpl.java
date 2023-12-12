package com.star.wlh.resource;

import com.star.wlh.common.dao.mongo.AbstractMongoGenDao;
import org.springframework.stereotype.Component;

/**
 * @Classname ResourceDaoImpl
 * @Description 类型 Dao实现类。
 * @Date 2023/6/11 17:57
 * @Created by wlh
 */
@Component
public class ResourceDaoImpl extends AbstractMongoGenDao<ResourceEntity> implements ResourceDao {
    @Override
    protected Class<ResourceEntity> getEntityClass() {
        return ResourceEntity.class;
    }
    @Override
    public void save(ResourceEntity resource){

    }
}
