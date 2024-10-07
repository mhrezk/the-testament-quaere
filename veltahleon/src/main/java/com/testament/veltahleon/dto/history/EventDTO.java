package com.testament.veltahleon.dto.history;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private Long id;
    private String incident;
    private String beginningDay;
    private String beginningMonth;
    private String beginningYear;
    private String endingDay;
    private String endingMonth;
    private String endingYear;
    private String description;

    public String getBeginningEventYear() {
        return beginningDay + "/" + beginningMonth + "/" + beginningYear;
    }

    public String getEndingEventYear() {
        return endingDay + "/" + endingMonth + "/" + endingYear;
    }
}
