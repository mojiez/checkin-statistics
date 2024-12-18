package com.atyichen.checkinstatistics.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.atyichen.checkinstatistics.excel.listener.PersonRecordListener;
import com.atyichen.checkinstatistics.excel.model.HolidayInfo;
import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import com.atyichen.checkinstatistics.excel.service.ExcelService;
import com.atyichen.checkinstatistics.excel.service.HolidayInfoService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExcelServiceImpl implements ExcelService {
    // 保存原有格式
    private boolean isBold = false;
    private static final String detailFinal = "/Users/mojie/Desktop/daka/detail.xlsx";
    private int fontSize = 12;
    private String fontFamily = "宋体";
    @Resource
    private HolidayInfoService holidayInfoService;

    @Value("${file.template.path}") // 配置化文件路径
    private String templatePath;

    @Override
    public Map<String, List<PersonRecord>> getNameToRecordWithHoliday(MultipartFile file) {
        // 读本地
//        String excelPath = "/Users/mojie/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/7539f579f077b66451e3c6ab27441be0/Message/MessageTemp/61a28f93a36f4e05df228d6c60475822/File/企业人员通行记录导出表(1).xlsx";

        // 通过上传的文件读
        PersonRecordListener personRecordListener = new PersonRecordListener();
        try {
            EasyExcel.read(file.getInputStream(), PersonRecord.class, personRecordListener).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return personRecordListener.getNameToRecordsWithHoliday();
    }

    @Override
    public Map<String, List<PersonRecord>> getNameToRecordExcludeHoliday(MultipartFile file) {
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
        Map<String, List<PersonRecord>> withHolidayMap = this.getNameToRecordWithHoliday(file);
        String ym = "";
        Map.Entry<String, List<PersonRecord>> first = withHolidayMap.entrySet().iterator().next();
        String[] s = first.getValue().get(0).getAccessTime().split(" ")[0].split("-");
        ym = s[0] + "-" + s[1];
        System.out.println("年月是: " + ym);
        System.out.println("测试一下打包嗷嗷");

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
        withHolidayMap.forEach((name, records) -> {
            System.out.println("打卡者: " + name + " 次数: " + records.size());
        });
        return withHolidayMap;
    }

    @Override
    public String writeDetailExcel(Map<String, List<PersonRecord>> nameToRecord) {
        // 表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);

        // 内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 创建样式策略
        HorizontalCellStyleStrategy styleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        String fileName = templatePath + "/detail.xlsx";
        // 1. 检查并创建目录
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // 2. 如果文件存在， 先删除
        if (file.exists()) {
            file.delete();
        }

        // 3. 创建要写入的List
        List<PersonRecord> dataList = new ArrayList<>();
        nameToRecord.forEach((name, records) -> {
            dataList.addAll(records);
        });

        // 3. 写入excel
        try {
            EasyExcel.write(fileName, PersonRecord.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .registerWriteHandler(styleStrategy)
                    .sheet("Sheet1")
                    .doWrite(dataList);
            System.out.println("excel写入成功");
            return fileName;
        }catch (Exception e) {
            throw new RuntimeException("excel写入失败", e);
        }
    }

    private void clearParagraph(XWPFParagraph paragraph) {
        for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }
    }

    private String formatYearMonth(String ym) {
        return ym.substring(0, 4) + "年" + ym.substring(4, 6) + "月研究院打卡统计（李黎团队）";
    }

    @Override
    public String writeSignExcel(Map<String, List<PersonRecord>> nameToRecord) {
//        String fileName = "/Users/mojie/Desktop/daka/sign.docx";
        String fileName = templatePath + "/sign.docx";
        String ym = getYm(nameToRecord);
        // 1. 先完整读取文件
        XWPFDocument document;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            document = new XWPFDocument(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            // 修改表格中的打卡次数
            XWPFTable table = document.getTables().get(0);

            // 修改表头的月份
            XWPFTableRow row1 = table.getRow(0);
            XWPFTableCell cell1 = row1.getCell(0);
            XWPFParagraph paragraph = cell1.getParagraphs().get(0);
            System.out.println("打印这一行");
            System.out.println(row1.getCell(0).getText());

            // 保存之前单元格的格式
            saveFormat(paragraph);

            // 清除现有内容
            clearParagraph(paragraph);

            // 添加新内容并应用原有格式
            setCellContent(paragraph, ym, null, null);

            for (int i = 2; i < table.getRows().size()-1; i++) {
                XWPFTableRow row = table.getRow(i);

                XWPFTableCell cell = row.getCell(2);
                XWPFParagraph paragraph1 = cell.getParagraphs().get(0);
                // 保存之前单元格的格式
                saveFormat(paragraph1);

                // 清除现有内容
                clearParagraph(paragraph1);

                // 添加新内容并应用原有格式
                setCellContent(paragraph1, ym, nameToRecord, row.getCell(1).getText());
            }

            // 修改表头
            XWPFTableRow row = table.getRow(table.getRows().size()-1);
            XWPFTableCell cell = row.getCell(0);
            XWPFParagraph paragraph1 = cell.getParagraphs().get(0);

            // 保存之前单元格的格式
            saveFormat(paragraph1);

            // 清除现有内容
            clearParagraph(paragraph1);

            // 添加新内容并应用原有格式
            setCellContent(paragraph1, ym, nameToRecord, "last");

            // 3. 保存文档
            FileOutputStream fos = new FileOutputStream(fileName);
            document.write(fos);
            fos.close();

            // 4. 关闭文档
            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    public void saveFormat(XWPFParagraph paragraph) {
        if (!paragraph.getRuns().isEmpty()) {
            XWPFRun firstRun = paragraph.getRuns().get(0);
            isBold = firstRun.isBold();
            fontSize = firstRun.getFontSize();
            fontFamily = firstRun.getFontFamily();
        }
    }

    private void setCellContent(XWPFParagraph paragraph, String ym, Map<String, List<PersonRecord>> nameToRecord, String name) {
        XWPFRun run = paragraph.createRun();
        if (nameToRecord == null)
            run.setText(formatYearMonth(ym));
        else {
            if ("last".equals(name)) {
                run.setText(String.format("%s.%s.30", ym.substring(0, 4), ym.substring(4, 6)));
                return;
            }
            List<PersonRecord> rocords = nameToRecord.getOrDefault(name, new ArrayList<>());
            if (rocords.isEmpty()) {
                run.setText("0");
            } else run.setText(String.valueOf(rocords.size()));
        }

        run.setBold(isBold);
        run.setFontSize(fontSize);
        run.setFontFamily(fontFamily);
    }

    public String getYm(Map<String, List<PersonRecord>> nameToRecord) {
//        Map.Entry<String, List<PersonRecord>> firstEntry = nameToRecord.entrySet().iterator().next();
//        List<PersonRecord> records = firstEntry.getValue();
//
//        String[] s = records.get(0).getAccessTime().split(" ")[0].split("-");
//        return s[0] + s[1];
        Map.Entry<String, List<PersonRecord>> setEntry = nameToRecord.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .findFirst()
                .orElse(null); // option为空时返回一个默认值
        if (setEntry == null) {
            throw new RuntimeException("所有人都没打卡!");
        }
        List<PersonRecord> records = setEntry.getValue();
        String[] s = records.get(0).getAccessTime().split(" ")[0].split("-");
        return s[0] + s[1];
    }
}
