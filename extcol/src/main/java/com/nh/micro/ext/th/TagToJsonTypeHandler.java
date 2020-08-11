package com.nh.micro.ext.th;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nh.micro.ext.ExtBeanWrapper;

/**
 * 
 * @Description:
 * @Author: ninghao
 * @Date: 2020年8月11日
 * @Version: 2.0
 */
@MappedTypes(com.nh.micro.ext.ExtBeanWrapper.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TagToJsonTypeHandler extends BaseTypeHandler<ExtBeanWrapper> {
	private Map jsonToMap(String value) {
		if (value == null || "".equals(value)) {
			return Collections.emptyMap();
		} else {
			return JSON.parseObject(value, new TypeReference<Map<String, Object>>() {
			});
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ExtBeanWrapper parameter, JdbcType jdbcType)
			throws SQLException {
		if(parameter.getIgnoreNull()){
			ps.setString(i, JSON.toJSONString(parameter.getInnerMap()));
		}else{
			ps.setString(i, JSON.toJSONString(parameter.getInnerMap(),SerializerFeature.WriteMapNullValue));
		}
	}

	public boolean isJson(String value){
		if(value==null || "".equals(value)){
			return false;
		}else{
			if(value.startsWith("{")){
				return true;
			}
		}
		return false;
	}

	@Override
	public ExtBeanWrapper getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value=rs.getString(columnName);
		Map innerMap=jsonToMap(value);
		ExtBeanWrapper extBeanTag=new ExtBeanWrapper();
		extBeanTag.setInnerMap(innerMap);
		return extBeanTag;
	}

	@Override
	public ExtBeanWrapper getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value=rs.getString(columnIndex);
		Map innerMap=jsonToMap(value);
		ExtBeanWrapper extBeanTag=new ExtBeanWrapper();
		extBeanTag.setInnerMap(innerMap);
		return extBeanTag;
	}

	@Override
	public ExtBeanWrapper getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value=cs.getString(columnIndex);
		Map innerMap=jsonToMap(value);
		ExtBeanWrapper extBeanTag=new ExtBeanWrapper();
		extBeanTag.setInnerMap(innerMap);
		return extBeanTag;
	}

}
