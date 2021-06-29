package com.denlaku.longan.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 *
 * @author tianx
 */
@Configuration
@MapperScan("com.denlaku.longan.dao")
public class MyBatisConfig {
}
