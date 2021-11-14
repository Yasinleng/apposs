package com.yasin.apposs

import android.app.Application
import com.yasin.oss.GsonSerializationProvider
import com.yasin.oss.MMKVOSSProvider
import com.yasin.oss.OSS

/**
 * 邮箱：lengyacheng@163.com
 * Created by yasin on 2021/11/14.
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        OSS.init(MMKVOSSProvider(this, null, GsonSerializationProvider()))
    }
}