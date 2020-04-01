package com.example.synthesis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author: bill
 * @create: 2020/03/21 16:40
 */
@Configuration
@MapperScan("com.example.synthesis.dao") //扫描dao
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //扫描pojo
        bean.setTypeAliasesPackage("com.example.synthesis.pojo");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //扫描mapper.xml文件
        bean.setMapperLocations(resolver.getResources("classpath*:/mapper/*.xml"));
        return bean.getObject();
    }


}
