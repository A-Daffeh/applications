package com.cs544.project.applications.repository;

import com.cs544.project.applications.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobApplicationDao extends JpaRepository<JobApplication, Long> {
    JobApplication getJobApplicationByApplicationId(Long id);
}
