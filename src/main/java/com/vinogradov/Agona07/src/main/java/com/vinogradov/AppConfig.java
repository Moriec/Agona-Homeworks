package com.vinogradov;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.vinogradov"})
public class AppConfig {
    // Можно же не использовать xml для конфигураций?????? :)
}