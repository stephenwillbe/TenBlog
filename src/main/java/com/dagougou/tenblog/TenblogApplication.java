package com.dagougou.tenblog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//在主类上添加dao接口扫描注解
@MapperScan({"com.dagougou.tenblog.*.dao"})
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class TenblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenblogApplication.class, args);
    }

}
