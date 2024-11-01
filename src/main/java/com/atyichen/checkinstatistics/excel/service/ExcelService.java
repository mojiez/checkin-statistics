package com.atyichen.checkinstatistics.excel.service;

import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ExcelService {
    Map<String, List<PersonRecord>> getNameToRecordWithHoliday(MultipartFile file);

    Map<String, List<PersonRecord>> getNameToRecordExcludeHoliday(MultipartFile file);

    String writeDetailExcel(Map<String, List<PersonRecord>> nameToRecord);
    String writeSignExcel(Map<String, List<PersonRecord>> nameToRecord);
}
