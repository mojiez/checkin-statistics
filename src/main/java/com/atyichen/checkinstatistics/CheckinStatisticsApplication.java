package com.atyichen.checkinstatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atyichen.checkinstatistics.excel.mapper")
public class CheckinStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckinStatisticsApplication.class, args);
    }

}
