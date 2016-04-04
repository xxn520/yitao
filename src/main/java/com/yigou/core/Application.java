package com.yigou.core;

import com.yigou.core.config.AppConfig;
import org.springframework.boot.SpringApplication;

/**
 * Created by m2mbob on 16/4/3.
 */
public class Application {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
