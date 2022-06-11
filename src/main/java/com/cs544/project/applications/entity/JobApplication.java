package com.cs544.project.applications.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue
    private Long applicationId;
    private String applicationDomain;
    private String applicantName; // we can omit the cv upload, and just keep track of few applicant info.
    private String applicantContact;
    private Long jobId;
}
