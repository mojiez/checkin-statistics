package com.atyichen.checkinstatistics.excel.service;

import com.atyichen.checkinstatistics.excel.model.PersonRecord;

import java.util.List;
import java.util.Map;

public interface ExcelService {
    Map<String, List<PersonRecord>> getNameToRecordWithHoliday();

    Map<String, List<PersonRecord>> getNameToRecordExcludeHoliday();

}
