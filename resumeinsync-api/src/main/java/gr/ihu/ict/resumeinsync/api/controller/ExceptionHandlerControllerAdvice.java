package gr.ihu.ict.resumeinsync.api.controller;

import gr.ihu.ict.resumeinsync.common.exceptions.InvalidPasswordException;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static <E_TYPE extends Exception> ResponseEntity<VndErrors> handleException(final E_TYPE e,
            final HttpStatus status) {
        final String message = e.getMessage();
        final String simpleName = e.getClass().getSimpleName();
        final VndErrors vndErrors = new VndErrors(
                (message == null) ? "Unknown Message" : message,
                (simpleName == null) ? "Unknown Simple Name" : simpleName
                );

        final ResponseEntity.BodyBuilder response = ResponseEntity.status(
                (status == null) ? HttpStatus.INTERNAL_SERVER_ERROR : status);
        return response.body(vndErrors);
    }

    @ExceptionHandler
    public ResponseEntity<VndErrors> handleInvalidPasswordException(
            final InvalidPasswordException invalidPasswordException) {
        return handleException(invalidPasswordException, HttpStatus.BAD_REQUEST);
    }
}
