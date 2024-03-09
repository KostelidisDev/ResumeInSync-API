package gr.ihu.ict.resumeinsync.service.util;

import gr.ihu.ict.resumeinsync.domain.entity.embeddable.DateRange;
import gr.ihu.ict.resumeinsync.domain.entity.system.exprerience.Experience;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.util.Date;
import java.util.Objects;

public abstract class ExperienceUtils {

    public static <T extends Experience> Try<T> optimizeEndDate(final T experience) {
        return Try.run(() -> Objects.requireNonNull(experience, "experience is null"))
                .flatMap(ignored -> Option.of(experience.getCurrently()).toTry())
                .flatMap(currently -> Option.of(experience.getDateRange()).toTry()
                        .flatMap(dateRange -> (currently)
                                ? optimizeEndDateCurrently(experience, dateRange)
                                : optimizeEndDateNotCurrently(experience, dateRange)));
    }

    public static <T extends Experience> Try<T> optimizeEndDateCurrently(final T experience,
            final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(experience, "experience is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).flatMap(ignored -> setNullEndDate(dateRange))
                .flatMap(generatedDateRange -> setDateRange(experience, generatedDateRange));
    }

    public static <T extends Experience> Try<T> optimizeEndDateNotCurrently(final T experience,
            final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(experience, "experience is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).map(ignored -> Option.of(dateRange.getEndDate()).isDefined())
                .flatMap(hasEndDate -> (hasEndDate)
                        ? Try.success(experience)
                        : setNowEndDate(dateRange)
                                .flatMap(generatedDateRange -> setDateRange(experience, generatedDateRange)));
    }

    public static <T extends Experience> Try<T> setDateRange(final T experience,
            final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(experience, "experience is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).map(ignored -> {
            experience.setDateRange(dateRange);
            return experience;
        });
    }

    public static <T extends Experience> Try<DateRange> setNowEndDate(final DateRange dateRange) {
        return Try.run(() -> Objects.requireNonNull(dateRange, "dateRange is null"))
                .map(ignored -> {
                    dateRange.setEndDate(new Date());
                    return dateRange;
                });
    }

    public static <T extends Experience> Try<DateRange> setNullEndDate(final DateRange dateRange) {
        return Try.run(() -> Objects.requireNonNull(dateRange, "dateRange is null"))
                .map(ignored -> {
                    dateRange.setEndDate(null);
                    return dateRange;
                });
    }
}
