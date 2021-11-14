package com.yasin.oss;

import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.yasin.oss.api.OSSProvider;
import com.yasin.oss.api.SerializationProvider;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/9/4.
 */
public class MMKVOSSProvider implements OSSProvider {
    private MMKV mk;
    private SerializationProvider ss = null;


    public MMKVOSSProvider(MMKV mk, SerializationProvider ss) {
        this.mk = mk;
        this.ss = ss;

    }

    public MMKVOSSProvider(Context context, String cryptoKey, SerializationProvider ss) {
        String rootDir = MMKV.initialize(context);
        Log.d("OSS", "mmkv rootDir: " + rootDir);
        mk = MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptoKey);
        this.ss = ss;

    }

    @Override
    public void setSerializationProvider(SerializationProvider service) {
        this.ss = service;

    }

    @Override
    public boolean contains(String key) {
        return mk.contains(key);
    }

    @Override
    public void put(String key, Object value) {
        if (value instanceof String) {
            mk.encode(key, (String) value);
        } else if (value instanceof Boolean) {
            mk.encode(key, (Boolean) value);
        } else if (value instanceof Integer) {
            mk.encode(key, (Integer) value);
        } else if (value instanceof Short) {
            mk.encode(key, (Short) value);
        } else if (value instanceof Long) {
            mk.encode(key, (Long) value);
        } else if (value instanceof Float) {
            mk.encode(key, (Float) value);
        } else if (value instanceof Double) {
            mk.encode(key, (Double) value);
        } else {
            if (ss == null) {
                Log.w("OSS", "SerializationProvider not set");
                return;
            }
            mk.encode(key, ss.toString(value));
        }

    }

    @Override
    public void putStringSet(String key, Set<String> value) {
        mk.encode(key, value);
    }

    @Override
    public <T> T get(String key, Type type) {
        if (!mk.contains(key)) {
            return null;
        }
        Object object = null;
        if (type.equals(String.class)) {
            object = mk.decodeString(key);
        } else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            object = mk.decodeBool(key);
        } else if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            object = mk.decodeInt(key);
        } else if (type.equals(Short.TYPE) || type.equals(Short.class)) {
            object = mk.decodeInt(key);
        } else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            object = mk.decodeLong(key);
        } else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            object = mk.decodeFloat(key);
        } else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            object = mk.decodeDouble(key);
        } else {
            String json = mk.decodeString(key);
            if (json == null) {
                return null;
            } else {
                if (ss == null) {
                    Log.w("OSS", "SerializationProvider not set");
                    return null;
                }
                object = ss.toObj(json, type);
            }

        }

        return (T) object;
    }

    @Override
    public Set<String> getStringSet(String key) {
        return mk.decodeStringSet(key);
    }

    @Override
    public void remove(String key) {
        mk.remove(key);
    }

    @Override
    public void clear() {
        mk.clear();
    }
}
