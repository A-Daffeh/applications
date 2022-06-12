package com.cs544.project.applications.repository;

import com.cs544.project.applications.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IJobApplicationDao extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByJobId(Long jobId);
    List<JobApplication> findByApplicationDomainIgnoreCase(String jobTitle);
    List<JobApplication> findByApplicantId(Long id);
}
