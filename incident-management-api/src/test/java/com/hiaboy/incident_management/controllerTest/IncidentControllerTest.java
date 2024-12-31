package com.hiaboy.incident_management.controllerTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@SpringBootTest
@AutoConfigureMockMvc
public class IncidentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception {
        mockMvc.perform(get("/incident/getList")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }


    @Test
    public void createTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("incidentName","incidentName1");
        json.put("incidentContent","this is a incident content");
        mockMvc.perform(put("/incident/create")
                        .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }

    @Test
    public void modifyTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("incidentName","incidentName1");
        json.put("incidentContent","this is two incident content");
        json.put("id",1);
        mockMvc.perform(put("/incident/modify")
                        .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())).andExpect(status().isOk());

    }

    @Test
    public void deleteTest() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id",1);
        mockMvc.perform(delete("/incident/delete/1")
                        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));

    }

}
