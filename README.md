# Mybatis

环境说明：

- jdk 8 +
-  MySQL 8.0.20 
- maven-3.6.3
- IDEA

学习前需要掌握：

- JDBC 
- MySQL 
- Java 
- 基础 
- Maven 
- Junit

## 1、Mybatis简介

![image-20200826200635932](https://i.loli.net/2020/08/26/2DysnctauKxfhbV.png)

### 1.1、什么是MyBatis

- MyBatis 是一款优秀的==持久层框架==
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集的过程 
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 实体类 【Plain Old Java Objects,普通的 Java对象】映射成数据库中的记录。 
- MyBatis 本是apache的一个开源项目ibatis, 2010年这个项目由apache 迁移到了google code，并 且改名为MyBatis 。 
- 2013年11月迁移到Github . 
- Mybatis官方文档 : http://www.mybatis.org/mybatis-3/zh/index.html 
- GitHub : https://github.com/mybatis/mybatis-3

### 1.2、持久化

- 持久化是将程序数据在持久状态和瞬时状态间转换的机制。
  - 即把数据（如内存中的对象）保存到可永久保存的存储设备中（如磁盘）。持久化的主要应用 是将内存中的对象存储在数据库中，或者存储在磁盘文件中、XML数据文件中等等。 
  - JDBC就是一种持久化机制。文件IO也是一种持久化机制。 
  - 在生活中 : 将鲜肉冷藏，吃的时候再解冻的方法也是。将水果做成罐头的方法也是。
- 为什么需要持久化服务呢？那是由于内存本身的缺陷引起的
  - 内存断电后数据会丢失，但有一些对象是无论如何都不能丢失的，比如银行账号等，遗憾的 是，人们还无法保证内存永不掉电。 
  - 内存过于昂贵，与硬盘、光盘等外存相比，内存的价格要高2~3个数量级，而且维持成本也 高，至少需要一直供电吧。所以即使对象不需要永久保存，也会因为内存的容量限制不能一直 呆在内存中，需要持久化来缓存到外存。

### 1.3、持久层

- 什么是持久层？
  - 完成持久化工作的代码块 . ----> dao层 【DAO (Data Access Object) 数据访问对象】 
  - 大多数情况下特别是企业级应用，数据持久化往往也就意味着将内存中的数据保存到磁盘上加 以固化，而持久化的实现过程则大多通过各种==关系数据库==来完成。
  -  不过这里有一个字需要特别强调，也就是所谓的“层”。对于应用系统而言，数据持久功能大多 是必不可少的组成部分。也就是说，我们的系统中，已经天然的具备了“持久层”概念？也许 是，但也许实际情况并非如此。之所以要独立出一个“持久层”的概念,而不是“持久模块”，“持久 单元”，也就意味着，我们的系统架构中，应该有一个相对独立的逻辑层面，专著于数据持久 化逻辑的实现. 
  - 与系统其他部分相对而言，这个层面应该具有一个较为清晰和严格的逻辑边界。 【说白了就 是用来操作数据库存在的！】

### 1.4、为什么需要Mybatis

- Mybatis就是帮助程序猿将数据存入数据库中 , 和从数据库中取数据 . 
- 传统的jdbc操作 , 有很多重复代码块 .比如 : 数据取出时的封装 , 数据库的建立连接等等... , 通过框 架可以减少重复代码,提高开发效率 . 
- MyBatis 是一个半自动化的==ORM框架 (Object Relationship Mapping) -->对象关系映射== 
- 所有的事情，不用Mybatis依旧可以做到，只是用了它，所有实现会更加简单！==技术没有高低之 分，只有使用这个技术的人有高低之别==
- MyBatis的优点
  - 简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个 sql映射文件就可以了，易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的 设计思路和实现。 
  - 灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统 一管理和优化。通过sql语句可以满足操作数据库的所有需求。 
  - 解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设 计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。 
  - 提供xml标签，支持编写动态sql。
- 最重要的一点，使用的人多！公司需要

## 2、MyBatis第一个程序

==思路流程：搭建环境-->导入Mybatis--->编写代码--->测试==

### 2.1、代码演示

\1. 搭建实验数据库

```sql
CREATE DATABASE `mybatis`;

USE `mybatis`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` int(20) NOT NULL,
`name` varchar(30) DEFAULT NULL,
`pwd` varchar(30) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `user`(`id`,`name`,`pwd`) values (1,'狂神','123456'),(2,'张
三','abcdef'),(3,'李四','987654');

```

\2. 导入MyBatis相关 jar 包

- GitHub上找

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.20</version>
</dependency>
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.2</version>
</dependency>
```

\3. 编写MyBatis核心配置文件

- 查看帮助文档

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC&amp;characterEncoding=utf8&amp;useUnicode=true&amp;useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/epfox/dao/UserMapper.xml"/>
    </mappers>
</configuration>

```

\4. 编写MyBatis工具类

- 查看帮助文档

```java
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
             sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取SqlSession连接
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}

```

\5. 创建实体类

```java
package com.epfox.pojo;

public class User {
    private int id;
    private String name;
    private String pwd;

    public User() {
    }

    public User(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
```

\6. 编写Mapper接口类

```java
package com.epfox.dao;

import com.epfox.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
}
```

\7.编写Mapper.xml配置文件

- namespace 十分重要，不能写错！

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.epfox.dao.UserMapper">
    <select id="getUserList" resultType="com.epfox.pojo.User">
    select * from mybatis.user
  </select>
</mapper>
```

\8.编写测试类

- Junit 包测试

```java
package com.epfox.dao;

import com.epfox.pojo.User;
import com.epfox.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userDao.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();

    }
}

```

\9. 运行测试

### 2.2、问题说明

可能出现问题说明：Maven静态资源过滤问题

```xml
<build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```

## 3、CRUD操作

### 3.1、namespace

​	\1. 将上面案例中的UserMapper接口改名为 UserDao； 

​	\2. 将UserMapper.xml中的namespace改为为UserDao的路径 .

​	\3. 再次测试

结论：

配置文件中namespace中的名称为对应Mapper接口或者Dao接口的完整包名,必须一致！

### 3.2、select

- select标签是mybatis中最常用的标签之一
- select语句有很多属性可以详细配置每一条SQL语句
  - id
    - 命名空间中唯一的标识符 
    - 接口中的方法名与映射文件中的SQL语句ID 一一对应
  - parameterType
    - 传入SQL语句的参数类型 。【万能的Map，可以多尝试使用】
  - resultType
    - SQL语句返回值类型。【完整的类名或者别名】

**需求：根据id查询用户**

\1. 在UserMapper中添加对应方法

```java
public interface UserMapper {
    List<User> getUserList();

    User selectUserById(int id);
}
```

\2. 在UserMapper.xml中添加Select语句

```java
<select id="selectUserById" resultType="com.epfox.pojo.User">
    select * from mybatis.user where id = #{id};
</select>
```

\3. 测试类中测试

```java
@Test
public void testselectUserById(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User user1 = mapper.selectUserById(2);
    System.out.println(user1);
    sqlSession.close();
}
```

课堂练习：根据 密码 和 名字 查询用户

思路一：直接在方法中传递参数

\1. 在接口方法的参数前加 @Param属性

\2. Sql语句编写的时候，直接取@Param中设置的值即可，不需要单独设置参数类型

```java
User selectUserByNP(@Param("username") String username,@Param("pwd") String pwd);

<select id="selectUserByNP" resultType="com.epfox.pojo.User">
    select * from user where name=#{1} and pwd=#{2}
</select>
```

思路二：使用万能的Map

\1. 在接口方法中，参数直接传递Map；

```java
User selectUserByNP2(Map<String,Object> map);
```

\2. 编写sql语句的时候，需要传递参数类型，参数类型为map

```xml
<select id="selectUserByNP2" parameterType="map" resultType="com.epfox.pojo.User">
    select * from user where name=#{username} and pwd=#{password}
</select>
```

\3. 在使用方法的时候，Map的 key 为 sql中取的值即可，没有顺序要求！

```java
Map<String,Object> map = new HashMap<String,Object>();
map.put("username","张三");
map.put("password","456");
User user = mapper.selectUserByNP2(map);
```

总结：

如果参数过多，我们可以考虑直接使用Map实现，如果参数比较少，直接传递参数即可

### 3.3、insert

我们一般使用insert标签进行插入操作，它的配置和select标签差不多！

**需求：给数据库增加一个用户**

方法一：

\1. 在UserMapper接口中添加对应的方法

```java
int addUser(User user);
```

\2. 在UserMapper.xml中添加insert语句

```xml
<insert id="addUser" parameterType="com.epfox.pojo.User">
    insert into user(id,name,pwd) values (#{id},#{name},#{pwd});
</insert>
```

\3. 测试

```java
    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(4);
        user.setName("李易");
        user.setPwd("888");
        int i = mapper.addUser(user);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
```

方法二：使用map

\1. 在UserMapper接口中添加对应的方法

```xml
int addUser2(Map<String, Object> map);
```

\2. 在UserMapper.xml中添加insert语句

```XML
<insert id="addUser2" parameterType="map">
    insert into user(id,name,pwd) values (#{userid},#{username},#{userpwd});
</insert>
```

\3. 测试

```java
    @Test
    public void addUser2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userid",5);
        map.put("username","李哥");
        map.put("userpwd","456");
        int i = mapper.addUser2(map);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
```

**注意点：增、删、改操作需要提交事务！**

### 3.4、update

我们一般使用update标签进行更新操作，它的配置和select标签差不多！

**需求：修改用户的信息**

\1. 同理，编写接口方法

```java
int updateUser(User user);
```

\2. 编写对应的配置文件SQL

```xml
<update id="updateUser" parameterType="com.epfox.pojo.User">
    update mybatis.user set name=#{name} , pwd=#{pwd} where id = #{id};
</update>
```

\3. 测试

```java
@Test
public void updateUser(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User user = mapper.selectUserById(1);
    user.setPwd("123");
    int i = mapper.updateUser(user);
    System.out.println(i);
    sqlSession.commit();
    sqlSession.close();
}
```

### 3.5、delete

我们一般使用delete标签进行删除操作，它的配置和select标签差不多！

**需求：根据id删除一个用户**

\1. 同理，编写接口方法

```java
int deleteUser(int id);
```

\2. 编写对应的配置文件SQL

```java
<delete id="deleteUser" parameterType="int">
    delete from mybatis.user where id = #{id};
</delete>
```

\3. 测试

```java
@Test
public void deleteUser(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    int i = mapper.deleteUser(5);
    System.out.println(i);
    sqlSession.commit();
    sqlSession.close();
}
```

小结：

- 所有的增删改操作都需要提交事务！ 
- 接口所有的普通参数，尽量都写上@Param参数，尤其是多个参数时，必须写上！ 
- 有时候根据业务的需求，可以考虑使用map传递参数！ 
- 为了规范操作，在SQL的配置文件中，我们尽量将Parameter参数和resultType都写上！

### 3.6、思考题

模糊查询like语句该怎么写?

第1种：在Java代码中添加sql通配符。

```java
List<User> userList = mapper.selectlike("%李%");
<select id="selectlike" resultType="com.epfox.pojo.User">
    select * from mybatis.user where name like #{name};
</select>
```

第2种：在sql语句中拼接通配符，会引起sql注入

```xml
List<User> userList = mapper.selectlike2("李");
<select id="selectlike2" resultType="com.epfox.pojo.User">
     select * from mybatis.user where name like "%"#{name}"%";
</select>
```

## 4、配置解析

### 4.1、核心配置文件

- mybatis-config.xml 系统核心配置文件 
- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 
- 能配置的内容如下：

```html
configuration（配置）
properties（属性）
settings（设置）
typeAliases（类型别名）
typeHandlers（类型处理器）
objectFactory（对象工厂）
plugins（插件）
environments（环境配置）
environment（环境变量）
transactionManager（事务管理器）
dataSource（数据源）
databaseIdProvider（数据库厂商标识）
mappers（映射器）
<!-- 注意元素节点的顺序！顺序不对会报错 -->
```

我们可以阅读 mybatis-config.xml 上面的dtd的头文件！【演示】

### 4.2、environments元素

```xml
<environments default="development">
    <environment id="development">
        <transactionManager type="JDBC">
        	<property name="..." value="..."/>
        </transactionManager>
        <dataSource type="POOLED">
            <property name="driver" value="${driver}"/>
            <property name="url" value="${url}"/>
            <property name="username" value="${username}"/>
            <property name="password" value="${password}"/>
        </dataSource>
    </environment>
</environments>
```

- 配置MyBatis的多套运行环境，将SQL映射到多个不同的数据库上，必须指定其中一个为默认运行 环境（通过default指定）

- 子元素节点：environment

  - 具体的一套环境，通过设置id进行区别，id保证唯一！

  - 子元素节点：transactionManager - [ 事务管理器 ]

      ```html
      <!-- 语法 -->
      <transactionManager type="[ JDBC | MANAGED ]"/>
      ```
      
      - 详情：[点击查看官方文档](https://mybatis.org/mybatis-3/zh/configuration.html#environments)
      - 这两种事务管理器类型都不需要设置任何属性。
      
  - 子元素节点：数据源（dataSource）

      - dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。

      - 数据源是必须配置的。

      - 有三种内建的数据源类型

          ```html
          type="[UNPOOLED|POOLED|JNDI]"
          ```

      - unpooled： 这个数据源的实现只是每次被请求时打开和关闭连接。

      - **pooled**： 这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来 , 这是一种使得 并发 Web 应用快速响应请求的流行处理方式。

      - jndi：这个数据源的实现是为了能在如 Spring 或应用服务器这类容器中使用，容器可以 集中或在外部配置数据源，然后放置一个 JNDI 上下文的引用。

      - 数据源也有很多第三方的实现，比如dbcp，c3p0，druid等等....

### 4.3、mappers元素

#### 4.3.1、mappers

- 映射器 : 定义映射SQL语句文件
- 既然 MyBatis 的行为其他元素已经配置完了，我们现在就要定义 SQL 映射语句了。但是首先我们 需要告诉 MyBatis 到哪里去找到这些语句。 Java 在自动查找这方面没有提供一个很好的方法，所 以最佳的方式是告诉 MyBatis 到哪里去找映射文件。你可以使用相对于类路径的资源引用， 或完 全限定资源定位符（包括 file:/// 的 URL），或类名和包名等。映射器是MyBatis中最核心 的组件之一，在MyBatis 3之前，只支持xml映射器，即：所有的SQL语句都必须在xml文件中配 置。而从MyBatis 3开始，还支持接口映射器，这种映射器方式允许以Java代码的方式注解定义SQL 语句，非常简洁。

#### 4.3.2、引入资源方式

```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
	<mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```

```xml
<!-- 使用完全限定资源定位符（URL） -->
<mappers>
	<mapper url="file:///var/mappers/AuthorMapper.xml"/>
</mappers>
```

```xml
<!--
使用映射器接口实现类的完全限定类名
需要配置文件名称和接口名称一致，并且位于同一目录下
-->
<mappers>
	<mapper class="org.mybatis.builder.AuthorMapper"/>
</mappers>
```

```xml
<!--
将包内的映射器接口实现全部注册为映射器
但是需要配置文件名称和接口名称一致，并且位于同一目录下
-->
<mappers>
	<package name="org.mybatis.builder"/>
</mappers>

```

#### 4.3.3、Mapper文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.epfox.dao.UserMapper">
</mapper>
```

- namespace中文意思：命名空间，作用如下：

  \1. namespace和子元素的id联合保证唯一 , 区别不同的mapper

  \2. 绑定DAO接口

  - namespace的命名必须跟某个接口同名
  - 接口中的方法与映射文件中sql语句id应该一一对应

  \3. namespace命名规则 : 包名+类名

MyBatis 的真正强大在于它的映射语句，这是它的魔力所在。由于它的异常强大，映射器的 XML 文件就 显得相对简单。如果拿它跟具有相同功能的 JDBC 代码进行对比，你会立即发现省掉了将近 95% 的代 码。MyBatis 为聚焦于 SQL 而构建，以尽可能地为你减少麻烦。

#### 4.4、Properties优化

数据库这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递。 [官方文档](https://mybatis.org/mybatis-3/zh/configuration.html#properties)

我们来优化我们的配置文件

第一步 ; 在资源目录下新建一个db.properties

```properties
driver = com.mysql.cj.jdbc.Driver
url = jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true
username = root
password = 123456
```

第二步 : 将文件导入properties 配置文件

```xml
<configuration>
    <properties resource="db.properties" />
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/epfox/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```

更多操作，可以查看官方文档！【演示带领学习】

- 配置文件优先级问题
- 新特性：使用占位符

#### 4.5、typeAliases优化

类型别名是为 Java 类型设置一个短的名字。它只和 XML 配置有关，存在的意义仅在于用来减少类完全 限定名的冗余。

```xml
<!--配置别名,注意顺序-->
<typeAliases>
	<typeAlias type="com.epfox.pojo.User" alias="User"/>
</typeAliases>
```

当这样配置时， ==User== 可以用在任何使用 ==com.epfox.pojo.User== 的地方。

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如:

```xml
<typeAliases>
	<package name="com.epfox.pojo"/>
</typeAliases>
```

每一个在包 com.kuang.pojo 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的 非限定类名来作为它的别名。

若有注解，则别名为其注解值。见下面的例子：

```xml
@Alias("user")
public class User {
...
}
```

【演示】去官网查看一下Mybatis默认的一些类型别名！

#### 4.6、其他配置浏览

##### 4.6.1、设置

- 设置（settings）相关 => 查看帮助文档 
  - 懒加载 
  - 日志实现 
  - 缓存开启关闭
- 一个配置完整的 settings 元素的示例如下：

```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
    <setting name="lazyLoadingEnabled" value="true"/>
    <setting name="multipleResultSetsEnabled" value="true"/>
    <setting name="useColumnLabel" value="true"/>
    <setting name="useGeneratedKeys" value="false"/>
    <setting name="autoMappingBehavior" value="PARTIAL"/>
    <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
    <setting name="defaultExecutorType" value="SIMPLE"/>
    <setting name="defaultStatementTimeout" value="25"/>
    <setting name="defaultFetchSize" value="100"/>
    <setting name="safeRowBoundsEnabled" value="false"/>
    <setting name="mapUnderscoreToCamelCase" value="false"/>
    <setting name="localCacheScope" value="SESSION"/>
    <setting name="jdbcTypeForNull" value="OTHER"/>
    <setting name="lazyLoadTriggerMethods"
    value="equals,clone,hashCode,toString"/>
</settings>
```

##### 4.6.2、类型处理器

[官方文档](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)

- 无论是 MyBatis 在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出 一个值时， 都会用类型处理器将获取的值以合适的方式转换成 Java 类型。 
- 你可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。【了解即可】

##### 4.6.3、对象工厂

[官方文档](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)

- MyBatis 每次创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完 成。
-  默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认构造方法，要么在参数映射存在的时 候通过有参构造方法来实例化。 
- 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现。【了解即可】

##### 4.7 生命周期和作用域

**作用域（Scope）和生命周期**

理解我们目前已经讨论过的不同作用域和生命周期类是至关重要的，因为错误的使用会导致非常严重的 并发问题。

我们可以先画一个流程图，分析一下Mybatis的执行过程！

![image-20200826222709104](https://i.loli.net/2020/08/26/FufUiVBqC7DlAcX.png)

**作用域理解**

- SqlSessionFactoryBuilder 的作用在于创建 SqlSessionFactory，创建成功后， SqlSessionFactoryBuilder 就失去了作用，所以它只能存在于创建 SqlSessionFactory 的方法中， 而不要让其长期存在。因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是 局部方法变量）。
- SqlSessionFactory 可以被认为是一个数据库连接池，它的作用是创建 SqlSession 接口对象。因为 MyBatis 的本质就是 Java 对数据库的操作，所以 SqlSessionFactory 的生命周期存在于整个 MyBatis 的应用之中，所以一旦创建了 SqlSessionFactory，就要长期保存它，直至不再使用 MyBatis 应用，所以可以认为 SqlSessionFactory 的生命周期就等同于 MyBatis 的应用周期。
- 由于 SqlSessionFactory 是一个对数据库的连接池，所以它占据着数据库的连接资源。如果创建多 个 SqlSessionFactory，那么就存在多个数据库连接池，这样不利于对数据库资源的控制，也会导 致数据库连接资源被消耗光，出现系统宕机等情况，所以尽量避免发生这样的情况。
- 因此在一般的应用中我们往往希望 SqlSessionFactory 作为一个单例，让它在应用中被共享。所以 说 SqlSessionFactory 的**最佳作用域是应用作用域**。

- 如果说 SqlSessionFactory 相当于数据库连接池，那么 SqlSession 就相当于一个数据库连接 （Connection 对象），你可以在一个事务里面执行多条 SQL，然后通过它的 commit、rollback 等方法，提交或者回滚事务。所以它应该存活在一个业务请求中，处理完整个请求后，应该关闭这 条连接，让它归还给 SqlSessionFactory，否则数据库资源就很快被耗费精光，系统就会瘫痪，所 以用 try...catch...finally... 语句来保证其正确关闭。
- **所以 SqlSession 的最佳的作用域是请求或方法作用域。**

![image-20200826223037997](https://i.loli.net/2020/08/26/HAzNhF9mKeu1cQ4.png)

## 5、ResultMap

**要解决的问题：属性名和字段名不一致**

环境：新建一个项目，将之前的项目拷贝过来

### 5.1、查询为null问题

\1. 查看之前的数据库的字段名

![image-20200826223734060](https://i.loli.net/2020/08/26/JRWXP8iMaFGDk51.png)

\2. Java中的实体类设计

```java
public class User {
    private int id; //id
    private String name; //姓名
    private String password; //密码和数据库不一样！
    //构造
    //set/get
    //toString()
}
```

\3. 接口

```java
//根据id查询用户
User selectUserById(int id);
```

\4. mapper映射文件

```xml
<select id="selectUserById" resultType="user">
	select * from user where id = #{id}
</select>
```

\5. 测试

```java
@Test
public void testSelectUserById() {
    SqlSession session = MybatisUtils.getSession(); //获取SqlSession连接
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserById(1);
    System.out.println(user);
    session.close();
}
```

结果:

- User{id=1, name='epfox', password='null'}

- 查询出来发现 password 为空 . 说明出现了问题！

分析：

- select * from user where id = #{id} 可以看做

  select id,name,pwd from user where id = #{id}

- mybatis会根据这些查询的列名(会将列名转化为小写,数据库不区分大小写) , 去对应的实体类中查找 相应列名的set方法设值 , 由于找不到setPwd() , 所以password返回null ; 【自动映射】

### 5.2、解决方案

方案一：为列名指定别名 , 别名和java实体类的属性名一致 

```xml
<select id="selectUserById" resultType="User">
	select id , name , pwd as password from user where id = #{id}
</select>
```

方案二：使用结果集映射->ResultMap 【推荐】

```xml
    <resultMap id="UserMap" type="User">
<!--        <result column="id" property="id"/>-->
<!--        <result column="name" property="name"/>-->
        <result column="pwd" property="password"/>
    </resultMap>

    <select id="selectUserById" resultMap="UserMap">
        select id,name,pwd from user where id = #{id};
    </select>
```

### 5.3、ResultMap

#### 5.3.1、自动映射

- ==resultMap== 元素是 MyBatis 中最重要最强大的元素。它可以让你从 90% 的 JDBC

  ==ResultSets== 数据提取代码中解放出来。

- 实际上，在为一些比如连接的复杂语句编写映射代码的时候，一份 ==resultMap== 能够代替实现同 等功能的长达数千行的代码。
- ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语 句只需要描述它们的关系就行了。

你已经见过简单映射语句的示例了，但并没有显式指定 resultMap 。比如：

```java
<select id="selectUserById" resultType="map">
    select id , name , pwd
    from user
    where id = #{id}
</select>
```

上述语句只是简单地将所有的列映射到 ==HashMap== 的键上，这由 ==resultType== 属性指定。虽然在 大部分情况下都够用，但是 HashMap 不是一个很好的模型。你的程序更可能会使用 JavaBean 或 POJO（Plain Old Java Objects，普通老式 Java 对象）作为模型。

==ResultMa==p 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。

#### 5.3.2、手动映射

\1. 返回值类型为resultMap

```xml
<select id="selectUserById" resultMap="UserMap">
	select id , name , pwd from user where id = #{id}
</select>
```

\2. 编写resultMap，实现手动映射！

```xml
<resultMap id="UserMap" type="User">
    <!-- id为主键 -->
    <id column="id" property="id"/>
    <!-- column是数据库表的列名 , property是对应实体类的属性名 -->
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>
```

如果世界总是这么简单就好了。但是肯定不是的，数据库中，存在一对多，多对一的情况，我们之后会 使用到一些高级的结果集映射，association，collection这些，我们将在之后讲解，今天你们需要把这 些知识都消化掉才是最重要的！理解结果集映射的这个概念！

