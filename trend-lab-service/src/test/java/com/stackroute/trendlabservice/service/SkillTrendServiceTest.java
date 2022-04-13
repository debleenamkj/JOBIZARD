package com.stackroute.trendlabservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.repository.SkillTrendRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SkillTrendServiceTest {

    @Mock
    SkillTrendRepository skillTrendRepository;

    @InjectMocks
    SkillTrendServiceImpl skillTrendService;

    private SkillTrend skillTrend;

    @BeforeEach
    void setUp() throws Exception{
        skillTrend = skillTrend = new SkillTrend(100000001l, "abc", "abc", 2001, 5000);
    }

    @Test
    public void saveSkillTest(){
        when(skillTrendRepository.save(any())).thenReturn(skillTrend);
        assertEquals(skillTrend,skillTrendService.saveSkill(skillTrend));
    }

    @Test
    public void updateSkillTest(){
        skillTrendRepository.deleteById(skillTrend.getSkillId());
        verify(skillTrendRepository).deleteById(skillTrend.getSkillId());
        when(skillTrendRepository.save(skillTrend)).thenReturn(skillTrend);
        assertEquals(skillTrend,skillTrendService.updateSkill(skillTrend));
    }

    @Test
    public void getAllSkillsTest(){
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        when(skillTrendRepository.findAll()).thenReturn(skillTrendList);
        assertEquals(1,skillTrendService.getAllSkills().size());
        skillTrendList.clear();
    }

    @Test
    public void getNameOfSkillsTest(){
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        skillTrendList.add(new SkillTrend(100000002l, "abc", "abc", 2005, 5000));
        when(skillTrendRepository.findAll()).thenReturn(skillTrendList);
        assertEquals(1,skillTrendService.getNameOfSkills().size());
        skillTrendList.clear();
    }

    @Test
    public void saveSkillNegativeTest(){
        when(skillTrendRepository.save(any())).thenReturn(skillTrend);
        assertNotEquals(skillTrend.getSkillId(),skillTrendService.saveSkill(skillTrend));
    }

    @Test
    public void updateSkillNegativeTest(){
        skillTrendRepository.deleteById(skillTrend.getSkillId());
        verify(skillTrendRepository).deleteById(skillTrend.getSkillId());
        when(skillTrendRepository.save(skillTrend)).thenReturn(skillTrend);
        assertNotEquals(true,skillTrendService.updateSkill(skillTrend));
    }

    @Test
    public void getAllSkillsNotTest(){
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        when(skillTrendRepository.findAll()).thenReturn(skillTrendList);
        assertNotEquals(5,skillTrendService.getAllSkills().size());
        skillTrendList.clear();
    }

    @Test
    public void getNameOfSkillsNegativeTest(){
        List<SkillTrend> skillTrendList = new ArrayList<>();
        skillTrendList.add(skillTrend);
        skillTrendList.add(new SkillTrend(100000002l, "abc", "abc", 2005, 5000));
        when(skillTrendRepository.findAll()).thenReturn(skillTrendList);
        assertNotEquals(100,skillTrendService.getNameOfSkills().size());
        skillTrendList.clear();
    }


}
