package com.batch.forex.util.check;

import com.batch.forex.util.bean.Failed;
import com.batch.forex.util.bean.Result;

public class ParamsCheck {
    private ParamsCheck(){}

    private static boolean checkCurrency(String currency) {
        return currency.split(",").length > 1;
    }

    public static Result check(String startDate, String endDate, String currency) {
        if (ParamsUtil.isNullOrEmpty(startDate, endDate, currency)) {
            return Failed.set("E002", "參數為空");
        } else if (DateUtil.parseDate(startDate, endDate)) {
            return Failed.set("E003", "日期轉型錯誤，請輸入 yyyyMMdd ");
        } else if (DateUtil.calcBetweenYears()) {
            return Failed.set("E001", "日期區間錯誤，請輸入一年以內");
        } else if (checkCurrency(currency)) {
            return Failed.set("E004", "參數不予輸入多筆");
        }
        return null;
    }
}
