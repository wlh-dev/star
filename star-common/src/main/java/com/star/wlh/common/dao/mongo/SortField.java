package com.star.wlh.common.dao.mongo;

import com.star.wlh.common.query.QueryParams.SortDirection;

import java.io.Serializable;

public class SortField implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字段的编码
     */
    private String field;

    /**
     * 排序方向
     */
    private SortDirection sort;

    public SortField() {
        // empty
    }

    public SortField(String field, SortDirection sort) {
        this.field = field;
        this.sort = sort;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SortDirection getSort() {
        return sort;
    }

    public void setSort(SortDirection sort) {
        this.sort = sort;
    }

}
