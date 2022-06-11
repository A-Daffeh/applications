package com.cs544.project.applications.service;

import com.cs544.project.applications.entity.JobApplication;
import com.cs544.project.applications.repository.IJobApplicationDao;
import com.cs544.project.applications.value_object.Company;
import com.cs544.project.applications.value_object.Job;
import com.cs544.project.applications.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class JobApplicationService {
    @Autowired
    private IJobApplicationDao jobApplicationDao;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseTemplate getJobApplicationByApplicationId(Long id) {
        ResponseTemplate vo = new ResponseTemplate();
        JobApplication jobApplication = jobApplicationDao.getJobApplicationByApplicationId(id);
        Job job = restTemplate.getForObject("http://JOB-SERVICE/jobs/" + jobApplication.getJobId(), Job.class);
        Company company = restTemplate.getForObject("http://COMPANY-SERVICE/companies/" + job.getCompanyId(), Company.class);
        vo.setJobApplication(jobApplication);
        vo.setJob(job);
        vo.setCompany(company);
        return vo;
    }

    public JobApplication addNewJobApplication(JobApplication jobApplication) {
        return jobApplicationDao.save(jobApplication);
    }

    public void update(JobApplication jobApplication) {
        jobApplicationDao.save(jobApplication);
    }
}
