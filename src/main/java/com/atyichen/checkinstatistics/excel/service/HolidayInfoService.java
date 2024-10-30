package com.atyichen.checkinstatistics.excel.service;

import com.atyichen.checkinstatistics.excel.model.HolidayInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface HolidayInfoService extends IService<HolidayInfo> {
    boolean saveHoliday();
    List<HolidayInfo> getHolidaysByMonth(String yearMonth);
}
