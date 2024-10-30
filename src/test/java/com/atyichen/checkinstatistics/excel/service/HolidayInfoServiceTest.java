package com.atyichen.checkinstatistics.excel.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HolidayInfoServiceTest {
    @Resource
    private HolidayInfoService holidayInfoService;
    @Test
    public void testHolidayService() {
        holidayInfoService.saveHoliday();
    }
}