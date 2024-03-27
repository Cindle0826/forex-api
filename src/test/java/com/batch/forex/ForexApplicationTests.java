package com.batch.forex;

import com.batch.forex.util.bean.Failed;
import com.batch.forex.util.bean.Success;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
@EnableScheduling
public class ForexApplicationTests {

    @Test
    public void success() {
        System.out.println(Success.set("Hello"));
    }

    @Test
    public void error() {
        System.out.println(Failed.set());
    }
}
