package com.batch.forex.controller;

import com.batch.forex.service.ForexService;
import com.batch.forex.util.bean.Success;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ForceControllerTests {

    @MockBean
    private ForexService forexService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 測試 controller 訊息回傳結果是否正常
     * @throws Exception exception
     */
    @Test
    public void testFetchForex() throws Exception {
        List<HashMap<String, String>> lh = new ArrayList<>();
        HashMap<String, String> hm1 = new HashMap<>();
        hm1.put("Date", "20240301");
        hm1.put("USD/NTD", "31.338");

        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("Date", "20240307");
        hm2.put("USD/NTD", "31.224");
        Collections.addAll(lh, hm1, hm2);

        Mockito.when(forexService.findByDateRange(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(lh);

        MockHttpServletRequestBuilder param = MockMvcRequestBuilders.get("/forex/fetch")
                .accept(MediaType.APPLICATION_JSON)
                .param("startDate", "20240301")
                .param("endDate", "20240320")
                .param("currency", "USD/NTD");

        String result = mockMvc.perform(param)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        Success<List<HashMap<String, String>>> readValue = objectMapper.readValue(result, new TypeReference<Success<List<HashMap<String, String>>>>() {
        });

        System.out.println("1 " + Success.set(lh));
        System.out.println("2 " + readValue);
        Assertions.assertEquals(Success.set(lh), readValue);
    }
}
