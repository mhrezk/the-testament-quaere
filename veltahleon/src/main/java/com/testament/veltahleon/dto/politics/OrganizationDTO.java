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
    private String founder;
    private String nation;
    private String urlSymbol;
    private Integer foundationDay;
    private Integer foundationMonth;
    private Integer foundationYear;
    private Integer disbandmentDay;
    private Integer disbandmentMonth;
    private Integer disbandmentYear;

    public String getFoundationDate() {
        return foundationDay + "/" + foundationMonth + "/" + foundationYear;
    }

    public String getDisbandmentDate() {
        return disbandmentDay + "/" + disbandmentMonth + "/" + disbandmentYear;
    }
}
