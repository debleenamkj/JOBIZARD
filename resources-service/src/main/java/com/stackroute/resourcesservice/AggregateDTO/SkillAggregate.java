package com.stackroute.resourcesservice.AggregateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
//@AllArgsConstructor
public class SkillAggregate {
    @Id
    private String category;
    private List<String> skillTypes;
}
