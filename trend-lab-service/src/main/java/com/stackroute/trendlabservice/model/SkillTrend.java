package com.stackroute.trendlabservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillTrend {

    @Id
    long skillId;
    String onDemandSkills;
    String industryName;
    int year;
    int skillDemandedByCompany;

}
