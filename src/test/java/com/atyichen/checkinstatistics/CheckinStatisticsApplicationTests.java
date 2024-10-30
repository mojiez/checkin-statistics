package com.atyichen.checkinstatistics;

import com.alibaba.excel.EasyExcel;
import com.atyichen.checkinstatistics.excel.listener.PersonRecordListener;
import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CheckinStatisticsApplicationTests {

    @Test
    public void testRead() {
        String excelPath = "/Users/mojie/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/7539f579f077b66451e3c6ab27441be0/Message/MessageTemp/61a28f93a36f4e05df228d6c60475822/File/企业人员通行记录导出表(1).xlsx";
        PersonRecordListener personRecordListener = new PersonRecordListener();
        EasyExcel.read(excelPath, PersonRecord.class, personRecordListener)
                .sheet()
                .doRead();

        // 打印查询结果
//        List<PersonRecord> records = personRecordListener.getRecords();
//        records.forEach(record -> {
//            System.out.println(record.toString());
////            System.out.println("时间: "+ record.getAccessTime());
//        });


    }


}
