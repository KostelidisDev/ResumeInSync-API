package gr.ihu.ict.resumeinsync.mapper;

import java.util.List;

public interface Mapper<SOURCE_CLASS, TARGET_CLASS> {

    TARGET_CLASS toDTO(SOURCE_CLASS object);

    List<TARGET_CLASS> toDTO(List<SOURCE_CLASS> objects);

    SOURCE_CLASS fromDTO(TARGET_CLASS TARGET_CLASS);

    List<SOURCE_CLASS> fromDTO(List<TARGET_CLASS> TARGET_CLASSES);
}
