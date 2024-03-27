package com.batch.forex.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Failed implements Result{
    private Status Failed;

    public Failed() {
        this("E000", "日期區間不符");
    }

    public Failed(String code, String message) {
        this.Failed = new Status(new Error(code, message));
    }

    @Data
    @AllArgsConstructor
    static class Status {
        private Error error;
    }

    public static Failed set() {
        return new Failed();
    }

    public static Failed set(String code, String message) {
        return new Failed(code, message);
    }
}
