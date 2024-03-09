package gr.ihu.ict.resumeinsync.service.util;

import gr.ihu.ict.resumeinsync.common.util.BooleanUtils;
import gr.ihu.ict.resumeinsync.domain.entity.embeddable.DateRange;
import gr.ihu.ict.resumeinsync.domain.entity.system.Certification;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.util.Date;
import java.util.Objects;

public abstract class CertificationUtils {

    public static Try<Certification> optimizeEndDate(final Certification certification) {
        return Try.run(() -> Objects.requireNonNull(certification, "certification is null"))
                .flatMap(ignored -> Option.of(certification.getCanExpire()).toTry())
                .map(BooleanUtils::NOT)
                .flatMap(canNotExpire -> Option.of(certification.getDateRange()).toTry()
                        .flatMap(dateRange -> (canNotExpire)
                                ? optimizeEndDateCanNotExpire(certification, dateRange)
                                : optimizeEndDateCanExpire(certification, dateRange)));
    }

    public static Try<Certification> optimizeEndDateCanNotExpire(final Certification certificate,
            final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(certificate, "certificate is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).flatMap(ignored -> setNullEndDate(dateRange))
                .flatMap(generatedDateRange -> setDateRange(certificate, generatedDateRange));
    }

    public static Try<Certification> optimizeEndDateCanExpire(final Certification certificate,
            final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(certificate, "certificate is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).map(ignored -> Option.of(dateRange.getEndDate()).isDefined())
                .flatMap(hasEndDate -> (hasEndDate)
                        ? Try.success(certificate)
                        : setNowEndDate(dateRange)
                                .flatMap(generatedDateRange -> setDateRange(certificate, generatedDateRange)));
    }

    public static Try<Certification> setDateRange(final Certification certificate, final DateRange dateRange) {
        return Try.run(() -> {
            Objects.requireNonNull(certificate, "certificate is null");
            Objects.requireNonNull(dateRange, "dateRange is null");
        }).map(ignored -> {
            certificate.setDateRange(dateRange);
            return certificate;
        });
    }

    public static Try<DateRange> setNowEndDate(final DateRange dateRange) {
        return Try.run(() -> Objects.requireNonNull(dateRange, "dateRange is null"))
                .map(ignored -> {
                    dateRange.setEndDate(new Date());
                    return dateRange;
                });
    }

    public static Try<DateRange> setNullEndDate(final DateRange dateRange) {
        return Try.run(() -> Objects.requireNonNull(dateRange, "dateRange is null"))
                .map(ignored -> {
                    dateRange.setEndDate(null);
                    return dateRange;
                });
    }
}
