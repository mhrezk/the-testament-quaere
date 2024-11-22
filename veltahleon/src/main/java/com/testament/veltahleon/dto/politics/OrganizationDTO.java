package com.testament.veltahleon.dto.politics;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrganizationDTO {
    private Long id;
    private String name;
    private String founderFirstName;
    private String founderSecondName;
    private String symbolURL;
    private Integer foundationDay;
    private Integer foundationMonth;
    private Integer foundationYear;
    private Integer disbandmentDay;
    private Integer disbandmentMonth;
    private Integer disbandmentYear;
}
