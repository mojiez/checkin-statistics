package com.atyichen.checkinstatistics.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRecord {
    @ExcelProperty("人员姓名")
    private String name;

    @ExcelProperty("账号/联系方式")
    private String contact;

    @ExcelProperty("所属企业")
    private String company;

    @ExcelProperty("出入区域")
    private String area;

    @ExcelProperty("出入时间")
    private String accessTime;

    @ExcelProperty("验证类型")
    private String verificationType;

    @ExcelProperty("出入类型")
    private String accessType;
}
