package com.nh.micro.ext.th;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nh.micro.ext.ExtBeanWrapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ninghao
 * @version 2.0
 */
@MappedTypes(com.nh.micro.ext.ExtBeanWrapper.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TagToJsonTypeHandler extends BaseTypeHandler<ExtBeanWrapper> {
    private JSON stringToJSON(String value) {
        if (value == null || "".equals(value)) {
            return null;
        } else {
            return value.startsWith("{") ? JSON.parseObject(value) : JSON.parseArray(value);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ExtBeanWrapper parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter.getIgnoreNull()) {
            ps.setString(i, JSON.toJSONString(parameter.getJson()));
        } else {
            ps.setString(i, JSON.toJSONString(parameter.getJson(), SerializerFeature.WriteMapNullValue));
        }
    }

    public boolean isJson(String value) {
        if (value == null || "".equals(value)) {
            return false;
        } else {
            if (value.startsWith("{")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ExtBeanWrapper getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        JSON json = stringToJSON(value);
        ExtBeanWrapper extBeanTag = new ExtBeanWrapper();
        extBeanTag.setJson(json);
        return extBeanTag;
    }

    @Override
    public ExtBeanWrapper getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        JSON json = stringToJSON(value);
        ExtBeanWrapper extBeanTag = new ExtBeanWrapper();
        extBeanTag.setJson(json);
        return extBeanTag;
    }

    @Override
    public ExtBeanWrapper getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        JSON json = stringToJSON(value);
        ExtBeanWrapper extBeanTag = new ExtBeanWrapper();
        extBeanTag.setJson(json);
        return extBeanTag;
    }

}
