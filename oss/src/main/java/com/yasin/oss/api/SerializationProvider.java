package com.yasin.oss.api;

import java.lang.reflect.Type;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public interface SerializationProvider {

    String toString(Object obj);

    <T> T toObj(String json, Type type);
}
