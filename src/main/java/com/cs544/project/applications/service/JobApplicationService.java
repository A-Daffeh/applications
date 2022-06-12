package com.cs544.project.applications.service;

import com.cs544.project.applications.entity.JobApplication;
import com.cs544.project.applications.repository.IJobApplicationDao;
import com.cs544.project.applications.value_object.Company;
import com.cs544.project.applications.value_object.Job;
import com.cs544.project.applications.value_object.JobFromJobService;
import com.cs544.project.applications.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JobApplicationService {
    @Autowired
    private IJobApplicationDao jobApplicationDao;
    @Autowired
    private RestTemplate restTemplate;

    public Job getJob(Long id){
        return restTemplate.getForObject("http://JOB-SERVICE/jobs/" + id, JobFromJobService.class).getJob();
    }

    public ResponseTemplate getJobApplicationByApplicationId(Long id) {
        ResponseTemplate vo = new ResponseTemplate();
        JobApplication jobApplication = jobApplicationDao.findById(id).orElse(null);
        assert jobApplication != null;
        Job job = getJob(jobApplication.getJobId());
        vo.setJobApplication(jobApplication);
        vo.setJob(job);
        return vo;
    }

    public JobApplication addNewJobApplication(JobApplication jobApplication) {
        Job job = getJob(jobApplication.getJobId());
        jobApplication.setApplicationDomain(job.getJobTitle());
        return jobApplicationDao.save(jobApplication);
    }

    public void update(JobApplication jobApplication) {
        jobApplicationDao.save(jobApplication);
    }

    public List<ResponseTemplate> getAllApplications(Long id, String type) {
        List<JobApplication> jobApplications = type.equals("applicant") ? jobApplicationDao.findByApplicantId(id) : jobApplicationDao.findByJobId(id);
        return getAllApplicationsOrAllByJobTitle(jobApplications);
    }

    public List<ResponseTemplate> getAllApplicationsByJobTitle(String jobTitle) {
        List<JobApplication> jobApplications = jobApplicationDao.findByApplicationDomainIgnoreCase(jobTitle);
        return getAllApplicationsOrAllByJobTitle(jobApplications);
    }

    public List<ResponseTemplate> getAllApplicationsOrAllByJobTitle( List<JobApplication> jobApplications) {
        List<ResponseTemplate> results = new ArrayList<>();
        jobApplications.forEach(app -> {
            Job job = getJob(app.getJobId());
            ResponseTemplate vo = new ResponseTemplate();
            vo.setJob(job);
            vo.setJobApplication(app);
            results.add(vo);
        });

        return results;
    }

    public void deleteApplicationForApplicant(Long applicationId) {
        jobApplicationDao.deleteById(applicationId);
    }
}
