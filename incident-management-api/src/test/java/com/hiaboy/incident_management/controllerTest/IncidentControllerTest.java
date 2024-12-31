package com.hiaboy.incident_management.controllerTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IncidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception {
        mockMvc.perform(post("/incident/getList")
                .contentType(MediaType.ALL).content("")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }


    @Test
    public void createTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("incidentName","incidentName1");
        json.put("incidentContent","this is a incident content");
        mockMvc.perform(post("/incident/create")
                        .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }

    @Test
    public void modifyTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("incidentName","incidentName1");
        json.put("incidentContent","this is two incident content");
        json.put("id",1);
        mockMvc.perform(post("/incident/modify")
                        .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())).andExpect(status().isOk());

    }

    @Test
    public void deleteTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id",1);
        mockMvc.perform(post("/incident/delete")
                        .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }

}
