package com.testament.veltahleon.entities.society;

import com.testament.veltahleon.entities.politics.enumeration.JobType;
import com.testament.veltahleon.entities.society.enumeration.JobStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String jobName;

    @Getter
    @Setter
    private JobStatus jobStatus;

    @Getter
    @Setter
    private JobType jobType;

    @Getter
    @Setter
    private Boolean isPundit;

}
