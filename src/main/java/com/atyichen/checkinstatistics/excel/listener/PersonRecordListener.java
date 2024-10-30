package com.atyichen.checkinstatistics.excel.listener;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atyichen.checkinstatistics.excel.model.PersonRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PersonRecordListener extends AnalysisEventListener<PersonRecord> {
    private final List<String> targetNames = Arrays.asList("张珂嘉", "宋元博", "茅天怡", "胡翌阳", "辛仁林", "陈依梁", "姚晨鸣", "金一非", "陶科伟", "张红斌", "陈泊宇");
    private final List<PersonRecord> records = new ArrayList<>();

    //  逐行读取数据
    @Override
    public void invoke(PersonRecord data, AnalysisContext analysisContext) {
        if (targetNames.contains(data.getName())) {
            records.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成, 找到 " + records.size() + "条记录");
    }

    public Map<String, List<PersonRecord>> getNameToRecordsWithHoliday() {
        // 不满足的被过滤
        Map<String, List<PersonRecord>> nameToRecord = records.stream()
                .filter(record -> "进".equals(record.getAccessType()))
                .collect(Collectors.groupingBy(PersonRecord::getName));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        nameToRecord.forEach((name, records) -> {
            List<PersonRecord> uniqueRecords = new ArrayList<>(records.stream()
                    .collect(Collectors.groupingBy(record -> record.getAccessTime().split(" ")[0], // 分类函数 按照 年月日进行分组 忽略时间
                            // 对分出来的每组数据进行处理
                            Collectors.collectingAndThen(
                                    Collectors.toList(),  // 1. 先把同一组的数据收集成List
                                    list -> list.stream()
                                            .min(new Comparator<PersonRecord>() {
                                                @Override
                                                public int compare(PersonRecord o1, PersonRecord o2) {
                                                    LocalDateTime t1 = LocalDateTime.parse(o1.getAccessTime(), formatter);
                                                    LocalDateTime t2 = LocalDateTime.parse(o2.getAccessTime(), formatter);
                                                    return t1.compareTo(t2);
                                                }
                                            })
                                            .orElse(null)
                            )))
                    .values());

            nameToRecord.put(name, uniqueRecords);
        });

//        System.out.println("得到 名字 对应 不同的每天 的 最早的时间");
//        nameToRecord.forEach((name, records) -> {
//            System.out.println("姓名: " + name + " 打卡情况:");
//            records.forEach(record -> System.out.println(record.toString()));
//        });

        // 查询数据库中的对应月份的假期， 如果records中的某条记录是假期，就排除调这条记录
        return nameToRecord;
    }
}
