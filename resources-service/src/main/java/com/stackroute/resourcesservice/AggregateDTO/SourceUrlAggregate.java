package com.stackroute.resourcesservice.AggregateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
//@AllArgsConstructor
public class SourceUrlAggregate {
    private @Id String skillType;
    private List<String> source;
}
