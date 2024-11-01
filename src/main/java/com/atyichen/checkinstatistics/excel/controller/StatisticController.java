package com.atyichen.checkinstatistics.excel.controller;

import com.atyichen.checkinstatistics.common.BaseResponse;
import com.atyichen.checkinstatistics.common.ErrorCode;
import com.atyichen.checkinstatistics.common.ResultUtil;
import com.atyichen.checkinstatistics.excel.model.PersonRecord;
import com.atyichen.checkinstatistics.excel.service.ExcelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sta")
public class StatisticController {
    @Resource
    private ExcelService excelService;

    // todo 统一结果返回 参考yupi
    @PostMapping("/get")
    public BaseResponse<Map<String, String>> uploadAndWrite(@RequestParam("file") MultipartFile file) {
        // todo 检查文件是否为空

        // todo 检查文件类型

        // 处理 excel文件
        Map<String, List<PersonRecord>> nameToRecordExcludeHoliday = excelService.getNameToRecordExcludeHoliday(file);

        String path1 = excelService.writeSignExcel(nameToRecordExcludeHoliday);
        String path2 = excelService.writeDetailExcel(nameToRecordExcludeHoliday);
        Map<String, String> fileToPath = new HashMap<>();
        fileToPath.put("sign", path1);
        fileToPath.put("detail", path2);
        return ResultUtil.success(fileToPath);
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }

        try {
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode(file.getName(), "UTF-8"));

            // 复制文件内容到响应流
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败");
        }
    }
}
