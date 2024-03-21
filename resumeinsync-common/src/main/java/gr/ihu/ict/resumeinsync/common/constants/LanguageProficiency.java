package gr.ihu.ict.resumeinsync.common.constants;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum LanguageProficiency {
    ELEMENTARY("Elementary proficiency"),
    LIMITED_WORKING("Limited working proficiency"),
    FULL_PROFESSIONAL("Professional working proficiency"),
    PROFESSIONAL_WORKING("Full professional proficiency"),
    NATIVE("Native or bilingual proficiency"),
    UNKNOWN("Please select");

    private final String value;

    LanguageProficiency(final String value) {
        this.value = value;
    }

    public static LanguageProficiency parse(final String value) {
        return Stream.of(LanguageProficiency.values())
                .filter(proficiency -> proficiency.getValue().equals(value))
                .findFirst()
                .orElse(LanguageProficiency.UNKNOWN);
    }
}
