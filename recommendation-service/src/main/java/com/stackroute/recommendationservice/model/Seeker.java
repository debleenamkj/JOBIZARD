package com.stackroute.recommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@NodeEntity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seeker {
  @Id
  private String email;
  private ArrayList education;
  private ArrayList skillSet;
}
