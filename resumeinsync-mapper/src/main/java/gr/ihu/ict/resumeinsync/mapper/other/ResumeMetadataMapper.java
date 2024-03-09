package gr.ihu.ict.resumeinsync.mapper.other;

import gr.ihu.ict.resumeinsync.domain.model.resume.metadata.ResumeMetadata;
import gr.ihu.ict.resumeinsync.dto.response.ResumeMetadataDTO;
import gr.ihu.ict.resumeinsync.mapper.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper
public interface ResumeMetadataMapper extends Mapper<ResumeMetadata, ResumeMetadataDTO> {

    @Override
    ResumeMetadataDTO toDTO(ResumeMetadata object);

    @Override
    List<ResumeMetadataDTO> toDTO(List<ResumeMetadata> objects);

    @Override
    @Mapping(target = "language", ignore = true)
    ResumeMetadata fromDTO(ResumeMetadataDTO TARGET_CLASS);

    @Override
    List<ResumeMetadata> fromDTO(List<ResumeMetadataDTO> TARGET_CLASSES);
}
