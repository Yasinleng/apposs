package com.yasin.oss;


import com.google.gson.Gson;
import com.yasin.oss.api.SerializationProvider;

import java.lang.reflect.Type;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public class GsonSerializationProvider implements SerializationProvider {
    private Gson gson;

    public GsonSerializationProvider() {
        gson = new Gson();
    }

    @Override
    public String toString(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * @param json
     * @param type 注意，集合类型的传递。如需要传List<Person>集合类型，应该这样 new TypeToken<List<Person>>() {}.getType()
     * @param <T>
     * @return
     */

    @Override
    public <T> T toObj(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
