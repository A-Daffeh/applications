package com.cs544.project.applications.value_object;

import com.cs544.project.applications.entity.JobApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {
    private JobApplication jobApplication;
    private Job job;
//    private Company company; the company owns the application, we can leave it out.
}
