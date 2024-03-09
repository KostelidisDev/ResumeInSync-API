package gr.ihu.ict.resumeinsync.common.constants;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public enum SystemLanguage {
    ENGLISH,
    GREEK;

    public static Optional<SystemLanguage> resolve(final String value) {
        if (Objects.isNull(value)) {
            return Optional.of(ENGLISH);
        }

        return Optional.of(SystemLanguage.values())
                .map(Stream::of)
                .map(languageStream -> languageStream
                        .filter(language -> {
                            final String name = language.name();
                            return name.equalsIgnoreCase(value);
                        }))
                .flatMap(Stream::findFirst);
    }
}
