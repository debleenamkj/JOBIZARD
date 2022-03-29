package com.stackroute.recruitmentservice.rabbitmq.domain;

import com.stackroute.recruitmentservice.model.JobDetails;

import java.util.List;

public class JobPostingDTO {
    private String companyId;
    private String companyName;
    private String companyMail;
    private String industryType;
    private byte[] logo;
    List<JobDetails> jobDetailsList;

    public JobPostingDTO() {
    }

    public JobPostingDTO(String companyId, String companyName, String companyMail, String industryType, byte[] logo, List<JobDetails> jobDetailsList) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyMail = companyMail;
        this.industryType = industryType;
        this.logo = logo;
        this.jobDetailsList = jobDetailsList;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public List<JobDetails> getJobDetailsList() {
        return jobDetailsList;
    }

    public void setJobDetailsList(List<JobDetails> jobDetailsList) {
        this.jobDetailsList = jobDetailsList;
    }
}
