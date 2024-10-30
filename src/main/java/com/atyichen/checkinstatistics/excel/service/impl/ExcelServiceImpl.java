package com.atyichen.checkinstatistics.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atyichen.checkinstatistics.excel.listener.PersonRecordListener;
import com.atyichen.checkinstatistics.excel.model.HolidayInfo;
import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import com.atyichen.checkinstatistics.excel.service.ExcelService;
import com.atyichen.checkinstatistics.excel.service.HolidayInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private HolidayInfoService holidayInfoService;

    @Override
    public Map<String, List<PersonRecord>> getNameToRecordWithHoliday() {
        // todo excel通过文件传递
        String excelPath = "/Users/mojie/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/7539f579f077b66451e3c6ab27441be0/Message/MessageTemp/61a28f93a36f4e05df228d6c60475822/File/企业人员通行记录导出表(1).xlsx";
        PersonRecordListener personRecordListener = new PersonRecordListener();
        EasyExcel.read(excelPath, PersonRecord.class, personRecordListener).sheet().doRead();
        return personRecordListener.getNameToRecordsWithHoliday();
    }

    @Override
    public Map<String, List<PersonRecord>> getNameToRecordExcludeHoliday() {
        // 查询数据库 获取这个月的所有假期的日期
//        final String[] ym = {""};
//        withHolidayMap.forEach((name, records) -> {
//            String[] s = records.get(0).getAccessTime().split(" ")[0].split("-");
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(s[0]).append(s[1]);
//            ym[0] = stringBuilder.toString();
//            System.out.println("年月是: " + ym[0]);
//            break;
//        });
        Map<String, List<PersonRecord>> withHolidayMap = this.getNameToRecordWithHoliday();
        String ym = "";
        Map.Entry<String, List<PersonRecord>> first = withHolidayMap.entrySet().iterator().next();
        String[] s = first.getValue().get(0).getAccessTime().split(" ")[0].split("-");
        ym = s[0] + "-" + s[1];
        System.out.println("年月是: " + ym);

        // 获取这个月的假期
        List<HolidayInfo> holidaysByMonth = holidayInfoService.getHolidaysByMonth(ym);
        System.out.println(ym + "的假期如下");
        for (HolidayInfo holidayInfo : holidaysByMonth) {
            System.out.println(holidayInfo.toString());
        }

        System.out.println("没有去掉假期的情况: ");
        withHolidayMap.forEach((name, records) -> {
            System.out.println("打卡者: " + name);
            records.forEach(record -> System.out.println(record.toString()));
        });

        Set<String> holidayList = holidaysByMonth.stream().map(HolidayInfo::getDate).collect(Collectors.toSet());
        // 将这个月的假期从 map中排除
        withHolidayMap.forEach((name, records) -> {
//            List<PersonRecord> collect = records.stream().collect(Collectors.groupingBy(record -> record.getAccessTime().split(" ")[0], Collectors.collectingAndThen(Collectors.toList(), list -> {
//                // 现在的list 的长度都是一
//                if (holidayList.contains(list.get(0).getAccessTime().split(" ")[0])) {
//                    // 如果包含
//                    return null;
//                }
//                return list.get(0);
//            }))).values().stream().filter(Objects::nonNull).collect(Collectors.toList());
            List<PersonRecord> recordsNoHoliday = records.stream().filter(record -> {
                // 如果不是假期 保留 （不是假期 返回true）
                return !holidayList.contains(record.getAccessTime().split(" ")[0]);
            }).collect(Collectors.toList());
            withHolidayMap.put(name, recordsNoHoliday);
        });
        System.out.println("去掉了假期的情况: ");
        withHolidayMap.forEach((name, records) -> {
            System.out.println("打卡者: " + name);
            records.forEach(record -> System.out.println(record.toString()));
        });

        // 只保留八点半以前打卡记录
        withHolidayMap.forEach((name, records) -> {
            List<PersonRecord> finalList = records.stream()
                    .filter(record -> {
                        LocalTime recordTime = LocalTime.parse(record.getAccessTime().split(" ")[1]);
                        return recordTime.isBefore(LocalTime.of(8, 30));
                    })
                    .collect(Collectors.toList());
            withHolidayMap.put(name, finalList);
        });

        System.out.println("只保留八点班以前的打卡记录的情况: ");
        withHolidayMap.forEach((name, records) -> {
            System.out.println("打卡者: " + name);
            records.forEach(record -> System.out.println(record.toString()));
        });

        System.out.println("打卡统计final: ");
        withHolidayMap.forEach((name, records)->{
            System.out.println("打卡者: " + name + " 次数: " + records.size());
        });
        return null;
    }
}
