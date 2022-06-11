package com.cs544.project.applications.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Long jobId;
    private String jobTitle;
    private String description;
    private Long companyId;
}
