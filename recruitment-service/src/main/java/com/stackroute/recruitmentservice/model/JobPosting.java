package com.stackroute.recruitmentservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(indexName = "jobs", type = "jobposting")
public class JobPosting {



    @Id
    private String companyId;
    private String companyName;
    private String companyMail;
    private String industryType;
    private byte[] logo;
    List<JobDetails> jobDetailsList;


}
