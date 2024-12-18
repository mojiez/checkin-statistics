package com.atyichen.checkinstatistics;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
@Slf4j
@SpringBootApplication
@MapperScan("com.atyichen.checkinstatistics.excel.mapper")
public class CheckinStatisticsApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CheckinStatisticsApplication.class, args);
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        log.info("Active profiles: {}", Arrays.toString(activeProfiles));
        System.out.println("测试启动ll");
    }

}
