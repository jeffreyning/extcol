package com.nh.micro.ext;


import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 *  ninghao
 */
public class ExtBeanWrapper {

	public ExtBeanWrapper() {
	};

	public ExtBeanWrapper(Object entity) {
		this.setObj(entity);
	}
	private boolean ignoreNull=true;
	

	public boolean getIgnoreNull() {
		return ignoreNull;
	}

	public void setIgnoreNull(boolean ignoreNull) {
		this.ignoreNull = ignoreNull;
	}
	private Map innerMap = new HashMap();

	public Map getInnerMap() {
		return innerMap;
	}

	public void setInnerMap(Map innerMap) {
		this.innerMap = innerMap;
	}

	public void setObj(Object entity) {
		if (entity == null) {
			innerMap = null;
		}
		JSON jobj = (JSON) JSON.toJSON(entity);
		innerMap = JSON.toJavaObject(jobj, Map.class);
	}

	public Object getObj() {
		if (innerMap == null) {
			return null;
		}
		JSON jobj = (JSON) JSON.toJSON(innerMap);
		Map entity = JSON.toJavaObject(jobj, Map.class);
		return entity;
	}

	public <T> T getObj(Class<T> targetClass){
		if (innerMap == null) {
			return null;
		}
		JSON jobj = (JSON) JSON.toJSON(innerMap);
		T entity = JSON.toJavaObject(jobj, targetClass);
		return entity;
	}
	public <T> T getObj(TypeReference<T> type) {
		JSON jobj = (JSON) JSON.toJSON(innerMap);
		return JSON.parseObject(jobj.toJSONString(), type);
	}
}
