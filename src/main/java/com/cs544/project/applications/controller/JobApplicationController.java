package com.cs544.project.applications.controller;

import com.cs544.project.applications.entity.JobApplication;
import com.cs544.project.applications.service.JobApplicationService;
import com.cs544.project.applications.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return jobApplicationService.addNewJobApplication(jobApplication);
    }



    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseTemplate updateJobApplication(@PathVariable Long id, @RequestBody JobApplication jobApplication) {
        if (id != jobApplication.getApplicationId())
            throw new IllegalArgumentException();
        jobApplicationService.update(jobApplication);
        return jobApplicationService.getJobApplicationByApplicationId(jobApplication.getApplicationId());
    }
}
