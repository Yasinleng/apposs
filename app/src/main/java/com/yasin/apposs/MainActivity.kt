package com.yasin.apposs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.yasin.oss.GsonSerializationProvider
import com.yasin.oss.OSS

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveObject()
        baseType()
    }

    private fun saveObject() {
        OSS.getInstance().setSerializationProvider(GsonSerializationProvider())
        val person = Person()
        person.setName("name")
        person.setAge(4)
        person.setSex('女')
        OSS.getInstance().put("person", person)
        val temp: Person = OSS.getInstance().get("person", Person::class.java)
        Log.i("id1234", "person = $temp")
        val personList: MutableList<Person> = ArrayList<Person>()
        for (i in 0..9) {
            val person1 = Person()
            person1.setName("name$i")
            person1.setAge(i)
            person1.setSex(if (i % 2 == 0) '男' else '女')
            personList.add(person)
        }
        OSS.getInstance().put("persons", personList)
        val persons: List<Person> =
            OSS.getInstance()["persons", object : TypeToken<List<Person?>?>() {}.getType()]
        Log.i("id1234", "persons = $persons")
    }


    private fun baseType() {

        OSS.getInstance().put("key", "test")
        Log.i("id1234", "value = " + OSS.getInstance()["key", String::class.java])

        OSS.getInstance().put("key", 1)
        Log.i(
            "id1234",
            "value = " + OSS.getInstance()["key", Int::class.javaPrimitiveType]
        )

        OSS.getInstance().put("key", 1f)
        Log.i("id1234", "value = " + OSS.getInstance()["key", Float::class.javaPrimitiveType])

        OSS.getInstance().put("key", 1L)
        Log.i("id1234", "value = " + OSS.getInstance()["key", Long::class.javaPrimitiveType])

        val b = true

        OSS.getInstance().put("key", b)
        Log.i("id1234", "value = " + OSS.getInstance()["key", Boolean::class.java])
    }
}