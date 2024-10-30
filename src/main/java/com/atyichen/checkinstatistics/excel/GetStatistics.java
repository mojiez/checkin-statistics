package com.atyichen.checkinstatistics.excel;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

public class GetStatistics {
    public void simpleRead() {

    }
    public void getHoliday() {
        String url = "http://timor.tech/api/holiday/year/%s?type=Y&week=Y";
        String response = HttpUtil.createGet(String.format(url, "2024")).execute().body();
        System.out.println(response);
    }

    public static void main(String[] args) {
        GetStatistics getStatistics = new GetStatistics();
        getStatistics.getHoliday();
    }
}
