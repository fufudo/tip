package com.fufufu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //开启定时任务
@SpringBootApplication
@MapperScan("com.fufufu.mapper")
public class TipApplication {

    public static void main(String[] args) {
        SpringApplication.run(TipApplication.class, args);
    }

}
