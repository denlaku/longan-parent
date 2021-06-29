package com.denlaku.longan.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置信息
 * @author tianx
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class BaseConfig {

}
