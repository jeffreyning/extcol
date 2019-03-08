package com.nh.micro.ext;


import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * 
 * @Description:
 * @Author: ninghao
 * @Date: 2019年3月4日
 * @Version: 1.0
 */
public class ExtBeanWrapper {

	public ExtBeanWrapper() {
	};

	public ExtBeanWrapper(Object entity) {
		this.setObj(entity);
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

	public Object getObj(Class targetClass) {
		if (innerMap == null) {
			return null;
		}
		JSON jobj = (JSON) JSON.toJSON(innerMap);
		Object entity = JSON.toJavaObject(jobj, targetClass);
		return entity;
	}

}
