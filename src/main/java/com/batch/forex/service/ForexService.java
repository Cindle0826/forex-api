package com.batch.forex.service;

import com.batch.forex.entity.ForexType;

import java.util.HashMap;
import java.util.List;

public interface ForexService {
    void saveAll();

    List<HashMap<String, String>> findByDateRange(String startDate, String endDate, String currency);
}
