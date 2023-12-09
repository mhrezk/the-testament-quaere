package com.testament.veltahleon.entities.politics;

import java.util.List;
import com.testament.veltahleon.abstraction.Human;
import com.testament.veltahleon.entities.calendar.Year;
import com.testament.veltahleon.entities.politics.enumeration.AuthoritativeStatus;
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
    private AuthoritativeStatus authoritativeStatus;

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
    private List<Pundit> vassals;

    @Getter
    @Setter
    private Boolean isSuzerain;

    @Getter
    @Setter
    private List<Pundit> suzerains;

    public Pundit() {}
}
