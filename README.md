# spring4_chapter02
Spring4 入门登录Demo，来源：精通Spring 4.X:企业应用开发实战精通 PDF


---

> 开发工具：intellij idea

> 构建方式：maven

> 部署服务器：tomcat7

---

> pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>test</groupId>
    <artifactId>spring4_chapter02</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <!-- spring 依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>

        <!-- 数据库连接池 mysql驱动 依赖 -->
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>${commons-dbcp.version}</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>

        <!--web依赖-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>${servlet.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.taglibs</groupId>
            <artifactId>taglibs-standard-impl</artifactId>
            <version>${taglibs-standard-impl.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>${javax.servlet.jstl.version}</version>
        </dependency>

        <!--test依赖-->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <!--&lt;!&ndash; jetty插件 &ndash;&gt;-->
            <!--<plugin>-->
                <!--<groupId>org.mortbay.jetty</groupId>-->
                <!--<artifactId>maven-jetty-plugin</artifactId>-->
                <!--<version>6.1.25</version>-->
                <!--<configuration>-->
                    <!--<connectors>-->
                        <!--<connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">-->
                            <!--<port>8000</port>-->
                            <!--<maxIdleTime>60000</maxIdleTime>-->
                        <!--</connector>-->
                    <!--</connectors>-->
                    <!--<contextPath>/bbs</contextPath>-->
                    <!--<scanIntervalSeconds>0</scanIntervalSeconds>-->
                <!--</configuration>-->
            <!--</plugin>-->

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.17</version>
                <configuration>
                    <parallel>methods</parallel>
                    <threadCount>10</threadCount>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <properties>
        <file.encoding>UTF-8</file.encoding>
        <spring.version>4.2.2.RELEASE</spring.version>
        <mysql.version>5.1.29</mysql.version>
        <servlet.version>3.0-alpha-1</servlet.version>
        <taglibs-standard-impl.version>1.2.5</taglibs-standard-impl.version>
        <javax.servlet.jstl.version>1.2</javax.servlet.jstl.version>
        <aspectj.version>1.8.1</aspectj.version>
        <commons-codec.version>1.9</commons-codec.version>
        <commons-dbcp.version>1.4</commons-dbcp.version>
        <hibernate.validator.version>5.0.2.Final</hibernate.validator.version>
        <jetty.version>8.1.8.v20121106</jetty.version>
        <slf4j.version>1.7.5</slf4j.version>
        <testng.version>6.8.7</testng.version>
    </properties>


</project>
```

---

> resources/smart-context.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 引用Spring的多个Schema 空间的格式定义文件 -->
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd
">

    <!-- 1 扫描类包，将Spring注解的类自动转化为Bean，同时完成Bean的注入 -->
    <context:component-scan base-package="com.smart.dao"/>

    <!-- 2 定义一个使用DBCP实现的数据源 -->
    <bean id="dataSource"
          class="org.apache.commons.dbcp.BasicDataSource"
          destroy-method="close"
          p:driverClassName="com.mysql.jdbc.Driver"
          p:url="jdbc:mysql://localhost:3306/sampledb"
          p:username="root"
          p:password="123456"
    />

    <!-- 3 定义JDBC模板Bean -->
    <bean id="jdbcTemplate"
          class="org.springframework.jdbc.core.JdbcTemplate"
          p:dataSource-ref="dataSource"
    />

    <!-- 2 扫描Service类包，应用Spring的注解配置 -->
    <context:component-scan base-package="com.smart.service"/>

    <!-- 3 配置事务管理器 -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
          p:dataSource-ref="dataSource"
    />

    <!-- 4 通过AOP配置提供事务增强，让Service包下所有Bean的所有方法拥有事务 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>
    <aop:config proxy-target-class="true">
        <aop:pointcut id="serviceMethod"
                      expression=" (execution(* com.smart.service..*(..))) and (@annotation(org.springframework.transaction.annotation.Transactional))"/>
        <aop:advisor pointcut-ref="serviceMethod" advice-ref="txAdvice"/>
    </aop:config>

</beans>
```

---

> webapp/WEB-INF/web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5"
    xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
     http://java.sun.com/xml/ns/javaee
     http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

    <welcome-file-list>
        <welcome-file>index.html</welcome-file>
    </welcome-file-list>

    <!-- 1 从类路径下加载Spring配置文件， classpath关键字特指类路径下加载 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:smart-context.xml</param-value>
    </context-param>

    <!--  2 负责启动Spring容器的监听器，它将引用1 处 的上下文参数获得Spring配置文件的地址 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!--  3 Spring MVC 的主控Servlet -->
    <servlet>
        <servlet-name>smart</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    </servlet>
    <!-- Spring MVC 处理的URL -->
    <servlet-mapping>
        <servlet-name>smart</servlet-name>
        <url-pattern>*.html</url-pattern>
    </servlet-mapping>

</web-app>

```

---

> webapp/WEB-INF/smart-servlet.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 1 扫描web包，应用spring的注解 -->
    <context:component-scan base-package="com.smart.web" />

    <!--  2 配置视图解析器，将ModelAndView 及字符串解析为具体的页面  -->
    <bean
        class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        p:viewClass="org.springframework.web.servlet.view.JstlView"
        p:prefix="/WEB-INF/jsp/"
        p:suffix=".jsp"
    />

</beans>
```

---

end
