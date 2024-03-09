package gr.ihu.ict.resumeinsync.service.exporter.config;

import gr.ihu.ict.resumeinsync.common.constants.date.DateFormat;
import io.vavr.control.Try;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class HandlebarsHelpersConfig {
    public static String now() {
        return new Date().toString();
    }

    public static String formatDate(final Date date) {
        return Try.run(() -> Objects.requireNonNull(date, "date is null"))
                .flatMap(ignored -> Try.of(() -> new SimpleDateFormat(DateFormat.DATE_FORMAT)))
                .flatMap(simpleDateFormat -> Try.of(() -> simpleDateFormat.format(date)))
                .getOrElse("???");
    }
}
