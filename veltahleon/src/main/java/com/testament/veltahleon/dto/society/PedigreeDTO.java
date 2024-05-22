package com.testament.veltahleon.dto.society;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedigreeDTO {
    private Long id;
    private String name;
    private List<String> spouses;
    private List<Long> pids;
    private String motherName;
    private Long mid;
    private String fatherName;
    private Long fid;
    private String gender;
    private String img;
}
