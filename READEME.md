# YXHPY电影网

* 本项目是一个小`demo`仅仅用于大家的学习使用
* 所有资源使用`python`爬虫自动创建到数据库的，因此如果存在违规电影请及时`联系我`
* 项目中存在很多不足，请酌情查看

### 项目预览

* [演示前台站](http://cwcc.cf/)

![image-20201113110333849](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110333849.png)

![image-20201113110358263](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110358263.png)

![image-20201113110424989](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110424989.png)

![image-20201113110523293](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110523293.png)

![image-20201113110542824](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110542824.png)

* [演示后台站](http://cwcc.cf/ssm/)

![image-20201113110605360](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110605360.png)

![image-20201113110618535](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110618535.png)

![image-20201113110628295](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110628295.png)

![image-20201113110644512](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113110644512.png)

### 数据库设计

![image-20201113143846805](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201113143846805.png)



### 存在问题

* java项目打包后会导致上传图片的功能开发环境和生产环境路径不同导致无法正常上传服务器，因此需考虑到这一点
* 未完成用户登录的功能，本项目主要练习springboot实现crud等基本操作
* 后端解决了跨域任何网站都可以进行访问，存在安全隐患
* 数据库设计和SQL语句联表查询可能存在问题可能导致查询缓慢
* 无访问权限管理，密码未加密处理