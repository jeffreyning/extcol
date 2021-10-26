package com.nh.micro.ext;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @author ninghao
 * @version 2.0
 */
public class ExtBeanWrapper {

    public ExtBeanWrapper() {
    }

    public ExtBeanWrapper(Object entity) {
        this.setObj(entity);
    }

    private boolean ignoreNull = true;


    public boolean getIgnoreNull() {
        return ignoreNull;
    }

    public void setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
    }

    private JSON json;

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    public void setObj(Object entity) {
        if (entity == null) {
            json = null;
        }
        json = (JSON) JSON.toJSON(entity);
    }

    public Object getObj() {
        if (json == null) {
            return null;
        }
        return json;
    }

    public <T> T getObj(Class<T> targetClass) {
        if (json == null) {
            return null;
        }
        return JSON.toJavaObject(json, targetClass);
    }

    public <T> T getObj(TypeReference<T> type) {
        return JSON.parseObject(json.toJSONString(), type);
    }

}
