package com.atyichen.checkinstatistics.excel.model;

import lombok.Data;

import java.util.Map;

// JSON响应对象
@Data
public class HolidayResponse {
    private Integer code;
    private Map<String, HolidayDetail> holiday;
    private Map<String, TypeInfo> type;

    @Data
    public static class HolidayDetail {
        private Boolean holiday;
        private String name;
        private Integer wage;
        private String date;
    }

    @Data
    public static class TypeInfo {
        private Integer type;
        private String name;
        private Integer week;
    }
}