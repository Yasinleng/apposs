package com.yasin.oss.api;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public interface OSSProvider {


    /**
     * 设置序列化服务
     */
    void setSerializationProvider(SerializationProvider service);

    /**
     * 判断是否存在 Key
     */
    boolean contains(String key);

    /**
     * 按键名存储对象
     */
    void put(String key,Object value);

    /**
     * 按键名存储 Set<String> 对象
     */
    void putStringSet(String key, Set<String> value);

    /**
     * 使用键名获取对应的对象
     */
    <T> T get(String key, Type type);

    /**
     * 使用键名获取对应的 Set<String> 对象
     */
    Set<String> getStringSet(String key);

    /**
     * 使用键名删除存储的对象
     */
    void remove(String key);


    void clear();

}
