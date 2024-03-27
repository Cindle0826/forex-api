package com.batch.forex.util.check;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParamsUtil<T> {
    private ParamsUtil() {
    }

    public static <R> boolean isNullOrEmpty(R ... params) {
        return Arrays.stream(params).anyMatch(param -> {
            if (param == null) {
                return true;
            } else if (param instanceof String) {
                return ((String) param).isEmpty();
            } else {
                return false;
            }
        });

    }
}
