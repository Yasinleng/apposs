package com.yasin.oss;


import com.yasin.oss.api.OSSProvider;
import com.yasin.oss.api.SerializationProvider;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 *
 * 使用需要添加依赖库
 *
 *     implementation 'com.tencent:mmkv-static:1.1.2'
 *     implementation 'com.google.code.gson:gson:2.8.6'
 */
public class OSS {

    private OSS() {
    }

    private static class SingletonInstance {
        private static final OSS instance = new OSS();
    }

    public static OSS getInstance() {
        return SingletonInstance.instance;
    }

    private static OSSProvider provider;

    public static void init(OSSProvider ossProvider) {
        provider = ossProvider;
    }

    public void setSerializationProvider(SerializationProvider serializationProvider) {
        ObjectCheckUtils.checkInit(provider);
        provider.setSerializationProvider(serializationProvider);
    }

    /**
     * 判断是否存在 Key
     */
    public boolean contains(String key) {
        ObjectCheckUtils.checkInit(provider);
        return provider.contains(key);
    }

    /**
     * 按键名存储对象
     */
    public void put(String key, Object value) {
        ObjectCheckUtils.checkInit(provider);
        provider.put(key, value);
    }

    /**
     * 按键名存储 Set<String> 对象
     */
    public void putStringSet(String key, Set<String> value) {
        ObjectCheckUtils.checkInit(provider);
        provider.putStringSet(key, value);
    }

    /**
     * 使用键名获取对应的对象
     */
    public <T> T get(String key, Class<T> type) {
        ObjectCheckUtils.checkInit(provider);
        return provider.get(key, type);
    }

    /**
     * 使用键名获取对应的对象
     */
    public <T> T get(String key, Type type) {
        ObjectCheckUtils.checkInit(provider);
        return provider.get(key, type);
    }

    /**
     * 使用键名获取对应的 Set<String> 对象
     */
    public Set<String> getStringSet(String key) {
        ObjectCheckUtils.checkInit(provider);
        return provider.getStringSet(key);
    }

    /**
     * 移除
     * @param key
     */
    public void remove(String key) {
        ObjectCheckUtils.checkInit(provider);
        provider.remove(key);
    }


    public void clear(){
        ObjectCheckUtils.checkInit(provider);
        provider.clear();
    }

}
