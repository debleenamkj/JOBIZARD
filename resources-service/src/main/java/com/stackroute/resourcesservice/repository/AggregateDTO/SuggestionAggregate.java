package com.stackroute.resourcesservice.repository.AggregateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionAggregate {
    private @Id String skillType;
    private List<String> source;
}
