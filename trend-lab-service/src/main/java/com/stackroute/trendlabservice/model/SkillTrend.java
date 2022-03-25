package com.stackroute.trendlabservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SkillTrend {

    @Id
    long skillId;
    String onDemandSkills;
    String industryName;



}
