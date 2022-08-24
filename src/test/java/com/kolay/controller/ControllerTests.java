package com.kolay.controller;

import com.kolay.config.DataSourceConfiguration;
import com.kolay.config.TestDataConfiguration;
import com.kolay.config.WebConfiguration;
import com.kolay.service.KpacService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfiguration.class,
        DataSourceConfiguration.class, TestDataConfiguration.class})
@WebAppConfiguration
public class ControllerTests {

    @Autowired
    private AppController appController;
    @Autowired
    private KpacService kpacService;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/newpac"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("kpac"))
                .andExpect(view().name("newpacpage"));
    }

    @Test
    void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/newpac")
                        .param("title", "Title")
                        .param("description", "Description")
                )
                .andExpect(status().is3xxRedirection());
    }
}
