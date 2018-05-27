package com.juhyung.board.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.properties")
@Configuration
@ComponentScan(basePackages = "com.juhyung.board.**.service")
@Import({DbConfig.class})
public class RootApplicationContextConfig {
}