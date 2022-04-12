package com.stackroute.trendlabservice.repository;

import com.stackroute.trendlabservice.model.SkillTrend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class SkillTrendRepositoryTest {

    @Autowired
    private SkillTrendRepository skillTrendRepository;

    private SkillTrend skillTrend;

    @BeforeEach
    void setUp() throws Exception {
        skillTrend = new SkillTrend(100000001, "abc", "abc", 2001, 5000);
    }

    @AfterEach
    void tearDown() throws Exception {
        skillTrendRepository.deleteById(100000001L);
    }

    @Test
    public void testInsertionOfSkillTrend() {

            skillTrendRepository.insert(skillTrend);
            SkillTrend skillTrendt2 = skillTrendRepository.findById(skillTrend.getSkillId()).get();
            assertEquals(skillTrendt2.getSkillId(), skillTrend.getSkillId());
    }

    @Test
    public void testSaveSkillTrend() {
        skillTrendRepository.save(skillTrend);
        SkillTrend skillTrendt2 = skillTrendRepository.findById(skillTrend.getSkillId()).get();
        assertEquals(skillTrendt2.getSkillId(), skillTrend.getSkillId());
    }

    @Test
    public void testDeleteSkillTrend() {
        skillTrendRepository.insert(skillTrend);
        SkillTrend skillTrendt2 = skillTrendRepository.findById(skillTrend.getSkillId()).get();
        skillTrendRepository.deleteById(skillTrendt2.getSkillId());
        assertEquals(Optional.empty(), skillTrendRepository.findById(skillTrend.getSkillId()));
    }

    @Test
    public void testRetrieveData() {
        skillTrendRepository.insert(skillTrend);
        assertEquals("abc",skillTrend.getIndustryName());
    }

    @Test
    public void testExistingData() {
        skillTrendRepository.insert(skillTrend);
        assertTrue(skillTrendRepository.existsById(skillTrend.getSkillId()));
    }

    @Test
    public void testNegativeInsertionOfSkillTrend() {
        skillTrendRepository.insert(skillTrend);
        SkillTrend skillTrendt2 = new SkillTrend(100000123l, "abc", "abc", 2001, 5000);
        assertNotEquals(skillTrendt2.getSkillId(), skillTrend.getSkillId());
        skillTrendRepository.deleteById(100000123l);
    }

    @Test
    public void testNegativeSaveSkillTrend() {
        skillTrendRepository.save(skillTrend);
        SkillTrend skillTrendt2 = new SkillTrend(100000123l, "abc", "abc", 2001, 5000);
        assertNotEquals(skillTrendt2.getSkillId(), skillTrend.getSkillId());
        skillTrendRepository.deleteById(100000123l);
    }

    @Test
    public void testNegativeDeleteSkillTrend() {
        skillTrendRepository.insert(skillTrend);
        SkillTrend skillTrendt2 = skillTrendRepository.findById(skillTrend.getSkillId()).get();
        skillTrendRepository.deleteById(skillTrendt2.getSkillId());
        assertNotEquals(null, skillTrendRepository.findById(skillTrend.getSkillId()));
    }

    @Test
    public void testNegativeRetrieveData() {
        skillTrendRepository.insert(skillTrend);
        assertNotEquals(null,skillTrend.getOnDemandSkills());
    }

    @Test
    public void testNegativeExistingData() {
        skillTrendRepository.insert(skillTrend);
        assertFalse(skillTrendRepository.existsById(0000000000000000000000000000l));
    }

}
