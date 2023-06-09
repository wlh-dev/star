package com.star.wlh.common.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 查询条件的构造辅助类。
 */
public class QueryBuilder<T> {
    private QueryParams<T> query;

    public QueryBuilder() {
        query = new QueryParams<>();
        query.setQueryItems(new QueryParamGroup(new ArrayList<>()));
    }

    /**
     * 添加查询条件，条件的比较符为等于。
     *
     * @param fieldName 属性字段名
     * @param value     属性值
     * @return 构造器对象
     */
    public QueryBuilder<T> addParam(String fieldName, Serializable value) {
        if (value instanceof QueryOperator) {
            return addParam(fieldName, (QueryOperator) value, null);
        }
        return addParam(fieldName, QueryOperator.EQ, value);
    }

    /**
     * 添加查询条件。
     *
     * @param fieldName 属性字段名
     * @param operator  比较符
     * @param value     属性值
     * @param values    其他值，用于支持多值的比较符
     * @return 构造器对象
     */
    public QueryBuilder<T> addParam(String fieldName, QueryOperator operator, Serializable value, Serializable... values) {
        List<Serializable> valueList = QueryParam.toParamValue(value);

        if (values != null && values.length > 0) {
            if (valueList == null) {
                valueList = new ArrayList<>(Arrays.asList(values));
            } else {
                valueList.addAll(Arrays.asList(values));
            }
        }

        QueryParam item = new QueryParam();
        item.setFieldName(fieldName);
        item.setQueryOperator(operator);
        item.setValues(valueList);
        query.getQueryItems().addItem(item);
        return this;
    }

    /**
     * 添加查询条件。
     *
     * @param item 查询条件对象
     * @return 构造器对象
     */
    public QueryBuilder<T> addParam(QueryItem item) {
        if (item != null) {
            query.addQueryParam(item);
        }
        return this;
    }

    /**
     * 设置为不分页。
     *
     * @return 构造器对象
     */
    public QueryBuilder<T> withoutPaging() {
        query.setPageSize(QueryParams.NO_PAGING);
        return this;
    }

    /**
     * 设置分页信息。
     *
     * @param pageNum  起始页，从 1 开始
     * @param pageSize 每页记录数
     * @return 构造器对象
     */
    public QueryBuilder<T> withPaging(int pageNum, int pageSize) {
        query.setPageNum(pageNum);
        query.setPageSize(pageSize);
        return this;
    }

    /**
     * 设置排序信息。
     *
     * @param direction  排序方向
     * @param orderField 排序字段
     * @return 构造器对象
     */
    public QueryBuilder<T> withOrdering(QueryParams.SortDirection direction, String... orderField) {
        SortField[] sorts = new SortField[orderField.length];
        for (int i = 0; i < orderField.length; i++) {
            sorts[i] = new SortField(orderField[i], direction);
        }
        query.setOrder(sorts);
        return this;
    }

    /**
     * 设置排序信息。
     *
     * @param orderFields 参与排序的各个字段
     * @return 构造器对象
     */
    public QueryBuilder<T> withOrdering(SortField... orderFields) {
        query.setOrder(orderFields);
        return this;
    }

    /**
     * 设置为需要统计查询结果记录总数。
     *
     * @return 构造器对象
     */
    public QueryBuilder<T> withCounting() {
        query.setNeedCount(true);
        return this;
    }

    /**
     * 设置期望返回值的属性。
     *
     * @param expectedFields 属性编码
     * @return 构造器对象
     */
    public QueryBuilder<T> withExpectedFields(String... expectedFields) {
        query.setExpectedFields(expectedFields);
        return this;
    }

    /**
     * 查询资源时，设置为仅返回可读性强的文本属性值（例如，引用类型的属性将返回引用对象的名称）。
     *
     * @return 构造器对象
     */
    public QueryBuilder<T> returnTextValue() {
        query.setReturnTextValue(true);
        return this;
    }

    /**
     * 是否查询逻辑删除的数据。
     *
     * @param value true、false
     * @return 构造器对象
     */
    public QueryBuilder<T> queryLogicallyDeleted(Boolean value) {
        query.setQueryLogicallyDeleted(value);
        return this;
    }

    /**
     * 指定本次查询需要使用的索引。
     *
     * @param hintIndex 索引名
     * @return 构造器对象
     */
    public QueryBuilder<T> withHintIndex(String hintIndex) {
        query.setHintIndex(hintIndex);
        return this;
    }


    /**
     * 返回构造好的查询条件对象。
     *
     * @return 查询条件对象
     */
    public QueryParams<T> end() {
        return query;
    }

}