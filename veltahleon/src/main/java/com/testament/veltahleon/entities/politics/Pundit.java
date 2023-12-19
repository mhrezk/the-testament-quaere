package com.testament.veltahleon.entities.politics;

import java.util.List;
import com.testament.veltahleon.abstraction.Human;
import com.testament.veltahleon.entities.calendar.Year;
import com.testament.veltahleon.entities.society.enumeration.JobType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pundits")
public class Pundit extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private List<JobType> jobType;

    @Getter
    @Setter
    private List<Organization> organization;

    @Getter
    @Setter
    private Year beginningYear;

    @Getter
    @Setter
    private Year endYear;

    public Pundit() {}
}
