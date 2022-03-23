package com.stackroute.recommendationservice.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;

@NodeEntity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
  @Id
  private String email;
  private ArrayList jobPreferences;
  private ArrayList skillSet;
}
