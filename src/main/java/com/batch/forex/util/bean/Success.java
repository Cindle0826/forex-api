package com.batch.forex.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
public class Success<T> implements Result{
    private Status<T> Success;

    public Success(T currency) {
        this(currency, "0000", "成功");
    }

    public Success(T currency, String code, String message) {
        this.Success = new Status<>(new Error(code, message), currency);
    }

    @Data
    @AllArgsConstructor
    static class Status<R> {
        private Error error;
        private R currency;
    }

    public static <T> Success<T> set(T currency) {
        return new Success<T>(currency);
    }

    public static <T> Success<T> set(T currency, String code, String message) {
        return new Success<T>(currency, code, message);
    }
}
