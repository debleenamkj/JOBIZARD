package com.stackroute.trendlabservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.service.SkillTrendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TrendControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private SkillTrendService service;

    @InjectMocks
    private TrendController controller;

    private SkillTrend skillTrend;

    @BeforeEach
    void setUp() throws Exception {
        skillTrend = new SkillTrend(100000001l, "abc", "abc", 2001, 5000);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void saveProductTest() throws Exception {
        when(service.saveSkill(any())).thenReturn(skillTrend);
        mockMvc.perform(
                        post("/api/v6/skills")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonToString(skillTrend))
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()
                );
        verify(service,times(1)).saveSkill(any());
    }

    @Test
    public void getSkillsTest() throws Exception {
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        when(service.getAllSkills()).thenReturn(skillTrendList);
        mockMvc.perform(
                get("/api/v6/getskills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(skillTrend))
        ).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        skillTrendList.clear();

    }

    @Test
    public void updateSkills() throws Exception{
        when(service.updateSkill(any())).thenReturn(skillTrend);
        mockMvc.perform(
                put("/api/v6/updateskill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(skillTrend))
        ).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getNamesTest() throws Exception{
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        when(service.getNameOfSkills()).thenReturn(skillTrendList);
        mockMvc.perform(
                        get("/api/v6/getnames")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonToString(skillTrend))
                ).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        skillTrendList.clear();
    }

    @Test
    public void deleteSkillTrendTest() throws Exception{
        when(service.deleteSkill(anyLong())).thenReturn(skillTrend);
        mockMvc.perform(
                delete("/api/v6/deleteskills/"+skillTrend.getSkillId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void testExternalApi() throws Exception{
//        mockMvc.perform(
//                get("/api/v6/salarys/dancer")
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    public static String jsonToString(final Object obj)throws JsonProcessingException
    {
        String result=null;

        try
        {
            ObjectMapper mapper=new ObjectMapper();
            String jsonContent=mapper.writeValueAsString(obj);
            result=jsonContent;
        }
        catch(JsonProcessingException e)
        {
            result="Json Processing Error";
        }
        return result;
    }
}
