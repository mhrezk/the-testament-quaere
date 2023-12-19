package com.testament.veltahleon.entities.politics;

import java.util.List;
import com.testament.veltahleon.entities.calendar.Year;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String battleName;

    @Getter
    @Setter
    private List<NationLeader> proponentNationalLeaders;

    @Getter
    @Setter
    private List<MilitaryLeader> proponentLeaders;

    @Getter
    @Setter
    private List<Army> proponentArmy;

    @Getter
    @Setter
    private List<NationLeader> opponentNationalLeaders;

    @Getter
    @Setter
    private List<MilitaryLeader> opponentLeaders;

    @Getter
    @Setter
    private List<Army> opponentArmy;

    @Getter
    @Setter
    private Year battleYear;

    @Getter
    @Setter
    private StringBuilder battleDescription;

    public Battle() {}
}
