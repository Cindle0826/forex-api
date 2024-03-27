package com.batch.forex.controller;

import com.batch.forex.entity.ForexType;
import com.batch.forex.service.ForexService;
import com.batch.forex.util.bean.Failed;
import com.batch.forex.util.bean.Result;
import com.batch.forex.util.bean.Success;
import com.batch.forex.util.check.DateUtil;
import com.batch.forex.util.check.ParamsCheck;
import com.batch.forex.util.check.ParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forex")
public class ForexController {

    @Autowired
    private ForexService forexService;

    @GetMapping("/test")
    public String test() {
        forexService.saveAll();
        return "Hello !";
    }

    /**
     *
     * @param startDate 起始日期
     * @param endDate 結束日期
     * @param currency 要查閱的匯率
     * @return json 格式的訊息 Success/Failed
     */
    @GetMapping("/fetch")
    public Result fetchForex(String startDate, String endDate, String currency) {

        return Optional.ofNullable(ParamsCheck.check(startDate, endDate, currency)).orElseGet(() -> {
            List<HashMap<String, String>> dataRange = forexService.findByDateRange(startDate, endDate, currency);
            return Success.set(dataRange);
        });
    }
}
