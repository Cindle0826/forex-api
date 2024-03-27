package com.batch.forex.service.impl;

import com.batch.forex.dao.ForexRepository;
import com.batch.forex.entity.ForexType;
import com.batch.forex.service.ForexService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForexServiceImpl implements ForexService {

    @Autowired
    private RestTemplate template;

    @Autowired
    private ForexRepository repository;

    @Override
    public void saveAll() {
        // API URL：https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates
        ResponseEntity<byte[]> response = template.getForEntity("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates",
                byte[].class);

        Optional.ofNullable(response.getBody()).ifPresent(bytes -> {
            String json = new String(bytes, StandardCharsets.UTF_8);
            ObjectMapper om = new ObjectMapper();
            try {
                List<ForexType> list = om.readValue(json, new TypeReference<List<ForexType>>() {});
                repository.saveAll(list);
                System.out.println("insert 筆數 => " + list.size());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<HashMap<String, String>> findByDateRange(String startDate, String endDate, String currency) {
        List<ForexType> dateRange = repository.findByDateRange(startDate, endDate);
        List<HashMap<String, String>> list = dateRange.stream().map(s -> s.getColumn(currency)).collect(Collectors.toList());
        System.out.println("查出幾筆 => " + list.size());
        return list;
    }
}
