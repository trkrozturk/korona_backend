package com.ante.grup.korona.controller;

import com.ante.grup.korona.model.KoronaData;
import com.ante.grup.korona.repository.DataRepository;
import com.ante.grup.korona.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    private String PROPER_INPUT_STRING = "20.04.2020 tarihinde Ankara da Korona virüs salgınında yapılan testlerde 15 yeni vaka bulundu. 15 kişi korona dan vefat etti. 15 kişide taburcu oldu.";
    private String EMPTY_INPUT_STRING = "";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @MockBean
    private DataRepository dataRepository;

    @Test
    public void when_inputStringIsProper_thenResponseStatusOk() throws Exception {
        when(dataService.save(new KoronaData())).thenReturn(new KoronaData());
        this.mockMvc.perform(post("/newRecord").content(PROPER_INPUT_STRING)).andExpect(status().isOk());
    }

    @Test
    public void when_inputStringIsEmpty_thenResponseStatusBadRequest() throws Exception {
        when(dataService.save(new KoronaData())).thenReturn(new KoronaData());
        this.mockMvc.perform(post("/newRecord").content(EMPTY_INPUT_STRING)).andExpect(status().isBadRequest());
    }

}