package gr.ihu.ict.resumeinsync.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecordCounts {
    private Integer professionalExperiences;
    private Integer volunteerExperiences;
    private Integer educations;
    private Integer certifications;
    private Integer skills;
    private Integer languages;
    private Integer publications;
}
