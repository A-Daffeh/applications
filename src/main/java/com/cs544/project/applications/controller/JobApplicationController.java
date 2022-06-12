package com.cs544.project.applications.controller;

import com.cs544.project.applications.entity.JobApplication;
import com.cs544.project.applications.service.JobApplicationService;
import com.cs544.project.applications.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseTemplate getJobApplication(@PathVariable Long id) {
        return jobApplicationService.getJobApplicationByApplicationId(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public JobApplication addNewJobApplication(@RequestBody JobApplication jobApplication) {
        jobApplication.setStatus("Review in progress");
        return jobApplicationService.addNewJobApplication(jobApplication);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseTemplate updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplication) {
        if (id != jobApplication.getApplicationId())
            throw new IllegalArgumentException();
        jobApplicationService.update(jobApplication);
        return jobApplicationService.getJobApplicationByApplicationId(jobApplication.getApplicationId());
    }

    @GetMapping(value = "/jobs/{id}", produces = "application/json")
    public List<ResponseTemplate> getAllApplicationsByJobId(@PathVariable(name = "id") Long jobId){
        return jobApplicationService.getAllApplications(jobId, "job");
    }

    @GetMapping(value = "/applicant/{id}", produces = "application/json")
    public List<ResponseTemplate> getAllApplicationsByApplicantId(@PathVariable(name = "id") Long applicantId){
        return jobApplicationService.getAllApplications(applicantId, "applicant");
    }

    @DeleteMapping(value = "/application/{id}", produces = "application/json")
    public void deleteApplicationForApplicant(@PathVariable(name = "id") Long applicationId){
        jobApplicationService.deleteApplicationForApplicant(applicationId);
    }

    @GetMapping(value = "/jobs/job/{jobTitle}", produces = "application/json")
    public List<ResponseTemplate> getAllApplicationsByJobTitle(@PathVariable String jobTitle){
        return jobApplicationService.getAllApplicationsByJobTitle(jobTitle);
    }
}
