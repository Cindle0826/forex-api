package com.batch.forex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;

@Document(collection = "forex")
@Data
public class ForexType {

    @Field(value = "Date")
    @JsonProperty("Date")
    private String Date;

    @Field(value = "USD/NTD")
    @JsonProperty("USD/NTD")
    private String USD_NTD;

    @Field(value = "RMB/NTD")
    @JsonProperty("RMB/NTD")
    private String RMB_NTD;

    @Field(value = "EUR/USD")
    @JsonProperty("EUR/USD")
    private String EUR_USD;

    @Field(value = "USD/JPY")
    @JsonProperty("USD/JPY")
    private String USD_JPY;

    @Field(value = "GBP/USD")
    @JsonProperty("GBP/USD")
    private String GBP_USD;

    @Field(value = "AUD/USD")
    @JsonProperty("AUD/USD")
    private String AUD_USD;

    @Field(value = "USD/HKD")
    @JsonProperty("USD/HKD")
    private String USD_HKD;

    @Field(value = "USD/RMB")
    @JsonProperty("USD/RMB")
    private String USD_RMB;

    @Field(value = "USD/ZAR")
    @JsonProperty("USD/ZAR")
    private String USD_ZAR;

    @Field(value = "NZD/USD")
    @JsonProperty("NZD/USD")
    private String NZD_USD;

    public HashMap<String, String> getColumn(String columnName) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("Date", getDate());

        switch (columnName) {
            case "USD/NTD":
                hm.put("USD/NTD", getUSD_NTD());
                break;
            case "RMB/NTD":
                hm.put("RMB/NTD", getRMB_NTD());
                break;
            case "EUR/USD":
                hm.put("EUR/USD", getEUR_USD());
                break;
            case "USD/JPY":
                hm.put("USD/JPY", getUSD_JPY());
                break;
            case "GBP/USD":
                hm.put("GBP/USD", getGBP_USD());
                break;
            case "AUD/USD":
                hm.put("AUD/USD", getAUD_USD());
                break;
            case "USD/HKD":
                hm.put("USD/HKD", getUSD_HKD());
                break;
            case "USD/RMB":
                hm.put("USD/RMB", getUSD_RMB());
                break;
            case "USD/ZAR":
                hm.put("USD/ZAR", getUSD_ZAR());
                break;
            case "NZD/USD":
                hm.put("NZD/USD", getNZD_USD());
                break;
            default:
                break;
        }

        return hm;
    }
}
