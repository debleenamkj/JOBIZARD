package com.stackroute.trendlabservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechNews {
    String title;
    String para;
    String img;
    String dateTime;
    String link;
}
