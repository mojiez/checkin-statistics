package com.atyichen.checkinstatistics.excel.service;

import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExcelServiceTest {
    @Resource
    private HolidayInfoService holidayInfoService;
    @Resource
    private ExcelService excelService;

    @Test
    public void testWithHoliday() {
        Map<String, List<PersonRecord>> nameToRecordWithHoliday = excelService.getNameToRecordWithHoliday();
        nameToRecordWithHoliday.forEach((name, records) -> {
            System.out.println("打卡者: " + name);
            records.forEach(record -> System.out.println(record.toString()));
        });
    }

    @Test
    public void testNoHoliday() {
        excelService.getNameToRecordExcludeHoliday();
    }
}