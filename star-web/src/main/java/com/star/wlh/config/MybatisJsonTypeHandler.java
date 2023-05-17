package com.star.wlh.config;

import com.star.wlh.common.serialize.Serialization;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Serializable.class})
public class MybatisJsonTypeHandler extends BaseTypeHandler<Serializable> {

    protected Class<Serializable> type;

    public MybatisJsonTypeHandler(Class<Serializable> type) {
        this.type=type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Serializable parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i,parameter);
    }

    @Override
    public Serializable getNullableResult(ResultSet rs, String columnName) throws SQLException {
        final String json = rs.getString(columnName);
        return rs.getObject(columnName, Serializable.class);
    }

    @Override
    public Serializable getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        final String json = rs.getString(columnIndex);
        return rs.getObject(columnIndex, Serializable.class);
    }

    @Override
    public Serializable getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        final String json = cs.getString(columnIndex);
        return cs.getObject(columnIndex, Serializable.class);
    }
}