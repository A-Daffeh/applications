package com.cs544.project.applications.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobFromJobService {
    private Job job;
    private Company company;
}