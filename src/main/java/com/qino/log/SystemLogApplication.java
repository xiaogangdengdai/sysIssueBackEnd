package com.qino.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统日志登记系统启动类
 */
@SpringBootApplication
@MapperScan("com.qino.log.mapper")
public class SystemLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemLogApplication.class, args);
    }
}
