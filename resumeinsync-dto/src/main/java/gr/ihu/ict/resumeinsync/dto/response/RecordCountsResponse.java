package gr.ihu.ict.resumeinsync.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordCountsResponse {
    private Integer professionalExperiences;
    private Integer volunteerExperiences;
    private Integer educations;
    private Integer certifications;
    private Integer skills;
    private Integer languages;
    private Integer publications;
}
