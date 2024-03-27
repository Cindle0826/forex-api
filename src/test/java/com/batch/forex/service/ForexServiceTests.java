package com.batch.forex.service;

import com.batch.forex.dao.ForexRepository;
import com.batch.forex.entity.ForexType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class ForexServiceTests {

    @Autowired
    private ForexService forexService;

    @MockBean
    ForexRepository forexRepository;

    /**
     * 測試 回傳結果是否正常
     */
    @Test
    public void findDateRange() {
        List<ForexType> types = new ArrayList<>();
        ForexType ft1 = new ForexType();
        ft1.setDate("20240301");
        ft1.setUSD_NTD("31.226");

        ForexType ft2 = new ForexType();
        ft2.setDate("20240320");
        ft2.setUSD_NTD("31.118");
        Collections.addAll(types, ft1, ft2);

        Mockito.when(forexRepository.findByDateRange(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(types);

        List<HashMap<String, String>> dateRange = forexService.findByDateRange("20240301", "20240320", "USD/NTD");

        HashMap<String, String> hm = new HashMap<>();
        hm.put("Date", "20240301");
        hm.put("USD/NTD", "31.226");

        Assertions.assertNotNull(dateRange);
        Assertions.assertEquals(dateRange.get(0).get("Date"), hm.get("Date"));
        Assertions.assertEquals(dateRange.get(0).get("USD/NTD"), hm.get("USD/NTD"));
    }
}
