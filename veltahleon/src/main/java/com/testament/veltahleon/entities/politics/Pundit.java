package com.testament.veltahleon.entities.politics;

import java.util.List;
import com.testament.veltahleon.abstraction.Human;
import com.testament.veltahleon.entities.calendar.Year;
import com.testament.veltahleon.entities.politics.enumeration.JobType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "pundits")
public class Pundit extends Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private List<Rank> ranks;

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

    @Getter
    @Setter
    private Boolean isVassal;

    @Getter
    @Setter
    private Boolean isSuzerain;

    public Pundit() {}
}
