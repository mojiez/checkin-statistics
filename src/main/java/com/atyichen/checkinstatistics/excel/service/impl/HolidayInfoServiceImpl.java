package com.atyichen.checkinstatistics.excel.service.impl;
import java.util.Date;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.atyichen.checkinstatistics.excel.mapper.HolidayInfoMapper;
import com.atyichen.checkinstatistics.excel.model.HolidayInfo;
import com.atyichen.checkinstatistics.excel.model.HolidayResponse;
import com.atyichen.checkinstatistics.excel.service.HolidayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class HolidayInfoServiceImpl extends ServiceImpl<HolidayInfoMapper, HolidayInfo>
    implements HolidayInfoService {

    @Override
    public boolean saveHoliday() {
        String url = "http://timor.tech/api/holiday/year/%s?type=N&week=Y";
        String response = HttpUtil.createGet(String.format(url, "2024")).execute().body();
        System.out.println(response);
        HolidayResponse holidayResponse = JSONUtil.toBean(response, HolidayResponse.class);
        if (holidayResponse.getCode() == 0) {
            List<HolidayInfo> holidayInfos = new ArrayList<>();

            holidayResponse.getHoliday().forEach((key, holidayInfo) -> {
                if (holidayInfo.getHoliday() != false) {
                    HolidayInfo holidayInfoAdd = new HolidayInfo();
                    holidayInfoAdd.setDate(holidayInfo.getDate());
                    holidayInfoAdd.setName(holidayInfo.getName());
                    holidayInfos.add(holidayInfoAdd);
                }
            });

            // 批量保存到数据库
            boolean b = this.saveBatch(holidayInfos);
            return b;
        }
        return false;
    }

    @Override
    public List<HolidayInfo> getHolidaysByMonth(String yearMonth) {
        QueryWrapper<HolidayInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("date", yearMonth)
                .orderByAsc("date");
        List<HolidayInfo> list = this.list(queryWrapper);
        return list;
    }
}




