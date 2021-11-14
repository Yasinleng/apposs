# apposs  应用对象存储的封装

使用方式，添加

~~~
第一步：
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
第二步

dependencies {
           //基础api 需要自己实现
	         implementation 'com.github.Yasinleng:apposs:oss-1.0.0'
           //实现了MMKV 存储方式
           implementation 'com.github.Yasinleng:apposs:oss-mmkv-1.0.0'
	}
  
~~~
