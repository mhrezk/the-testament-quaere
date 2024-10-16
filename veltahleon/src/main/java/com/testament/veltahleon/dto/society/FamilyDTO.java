package com.testament.veltahleon.dto.society;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FamilyDTO {
    private String id;
    private String motherID;
    private String fatherID;
    private List<String> spouseIDs;
    private String firstName;
    private String secondName;
    private String gender;
    private String communityName;
    private String imageURL;
}
