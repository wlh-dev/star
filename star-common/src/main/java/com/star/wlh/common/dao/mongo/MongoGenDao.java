package com.star.wlh.common.dao.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname MongoGenDao
 * @Description 
 * @Date 2023/6/6 22:13
 * @Created by wlh
 */
public interface MongoGenDao<T> {
    /**
     * 保存一个对象
     *
     * @param t 对象实体
     * @return
     */
    void save(T t);

    /**
     * 新建一个对象
     *
     * @param t 对象实体
     */
    void insert(T t);

    /**
     * 批量新对象。
     *
     * @param tList 对象实体List 待插入的对象列表
     */
    void insertMany(List<T> tList);

    /**
     * 根据ID删除记录，执行的是物理删除。
     */
    DeleteResult deleteById(String id);

    /**
     * 删除一条数据，执行的是物理删除。
     *
     * @param t 对象实体 t.getId（）不为空
     * @return
     */
    DeleteResult delete(T t);

    /**
     * 是否支持逻辑删除。
     *
     * @return 如果是返回 true，否则返回 false
     */
    boolean isLogicallyDelete();

    /**
     * 逻辑删除，如果逻辑删除被禁止，则执行物理删除操作。
     *
     * @param id ID
     * @param now 逻辑删除时的系统时间，可以为null，用于更新资源的更新时间
     * @return 成功删除的记录数
     */
    long deleteLogically(String id, Date now);

    /**
     * 逻辑删除，如果逻辑删除被禁止，则执行物理删除操作。
     *
     * @param id ID
     * @param now 逻辑删除时的系统时间，可以为null，用于更新资源的更新时间
     * @param version 逻辑删除时资源的新版本号
     * @return 成功删除的记录数
     */
    long deleteLogically(String id, Date now, long version);

    /**
     * 根据Id从Collection中查询对象。<br>
     * 需要说明的是，Mongdo自身没有主键自增机制，解决方法：
     * <ol>
     * <li>实体入库的时候，程序中为实体赋主键值.
     * <li>实体入库的时候，在mongoDB中自定义函数实现主键自增机制，定义方法同脚本代码类似
     * </ol>
     *
     * @param id 实体对象的Id,对应Collection中记录的_id字段.
     * @return 实体对象
     */
    T getById(String id);

    /**
     * 根据条件查询集合
     *
     * @param query 查询条件
     * @return 满足条件的集合
     */
    List<T> getList(Query query);

    /**
     * 根据条件查询集合
     *
     * @param query 查询条件
     * @param perferSecondary 从节点优先
     * @return 满足条件的集合
     */
    List<T> getList(Query query, boolean perferSecondary);

    /**
     * 通过条件查询单个实体
     *
     * @param query
     * @return
     */
    T getOne(Query query);

    /**
     * 通过条件进行分页查询
     *
     * @param query 查询条件
     * @param start 查询起始值 <strong> 类似mysql查询中的 limit start, size 中的 start</strong>
     * @param size 查询大小 <strong> 类似mysql查询中的 limit start, size 中的 size</strong>
     * @return 满足条件的集合
     */
    List<T> getPage(Query query, int start, int size);

    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     *
     * @param query 查询条件
     * @return 满足条件的记录总数
     */
    Long getCount(Query query);

    /**
     * 删除满足条件的所有对象。
     *
     * @param query 匹配条件
     * @return 结果信息
     */
    DeleteResult delete(Query query);

    /**
     * 更新满足条件的第一个记录
     *
     * @param query 匹配条件
     * @param update 更新操作
     * @return 结果信息
     */
    UpdateResult updateFirst(Query query, Update update);

    /**
     * 根据Id更新
     *
     * @param id ID
     * @param update 更新操作
     * @return 结果信息
     */
    UpdateResult updateById(String id, Update update);

    /**
     * 更新满足条件的所有记录
     *
     * @param query 匹配条件
     * @param update 更新操作
     * @return 结果信息
     */
    UpdateResult updateMulti(Query query, Update update);

    /**
     * 查找更新,如果没有找到符合的记录，则将更新的记录插入库中。
     *
     * @param query 匹配条件
     * @param update 更新操作
     * @return 结果信息
     */
    UpdateResult updateInser(Query query, Update update);

    /**
     * 查找符合条件的对象，并对其执行指定的操作（更新、删除）、甚至插入（开启 upsert 选项）。
     *
     * @param query 查询条件，用于匹配待修改的唯一对象，如果匹配多个则只作用于排序后的第一个对象
     * @param update 操作语句
     * @param options 操作选项
     * @return 执行操作的对象
     */
    T findAndModify(Query query, Update update, FindAndModifyOptions options);

    /**
     * 获取租户查询的Criteria
     *
     * @return Criteria
     */
    Criteria getTenantCriteria();

    /**
     * 基础查询条件：tenantId=xxx and deleted=false
     *
     * @param queryDeleted true: 只查询逻辑删除的，false: 只查询未逻辑删除的，null: 同时查询逻辑删除和未逻辑删除的
     * @return Criteria
     */
    Criteria getBaseCriteria(Boolean queryDeleted);

    /**
     * 返回集合对象：慎用！
     *
     * @return 集合
     */
    MongoCollection<Document> getCollection();


    /**
     * 创建集合
     */
    void createCollection();

    /**
     * 清除集合和数据：慎用！
     */
    void dropCollection();

    /**
     * 删除索引
     *
     * @param name
     */
    void dropIndex(String name);

    /**
     * 创建索引
     *
     * @param index
     */
    void createIndex(Index index);

    /**
     * 根据传入的排序参数设置排序
     *
     * @param sort 排序字段
     * @return 排序order list
     */
    public static List<Order> processSort(SortField[] sort)  {
        List<Order> order = new ArrayList<>();
        for (int i = 0; i < sort.length; i++) {
            String field = sort[i].getField();
            Order entity = new Order(Direction.ASC.name().equals(sort[i].getSort().name()) ? Direction.ASC : Direction.DESC, field);
            order.add(entity);
        }
        return order;
    }

    /**
     * 根据指定的条件执行查询操作，查询结果只包含指定的字段的值。
     *
     * @param query 查询条件
     * @param includedFileds 需要返回的属性字段
     * @return 查询结果
     */
    List<T> shrinkQuery(Query query, String... includedFileds);

    /**
     * 对符合条件的数据指定 distinct 操作。
     *
     * @param query 过滤条件
     * @param field 执行 distinct 操作的字段
     * @param resultClass 返回值类型
     * @return 结果值列表
     */
    <R> List<R> findDistinct(Query query, String field, Class<R> resultClass);
}
