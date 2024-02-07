package io.github.Marcky404.Biblioteca.exception;

import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControlExceptionHandler {


    public static final String CONSTRAINT_VALIDATION_FAILED = "Constraint validation failed";


    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {

        String path = getPath(request);
        log.info("Livraria BusinessException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode() != null ? ex.getHttpStatusCode().toString() : null, ex.getMessage(), ex.getDescription());
        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());

    }


    /**
     * @param request
     * @return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exMethod,
                                                                   WebRequest request) {
        String path = getPath(request);
        String error = exMethod.getName() + " should be " + exMethod.getRequiredType().getName();

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(CONSTRAINT_VALIDATION_FAILED)
                .description(error)
                .path(path)
                .build();

        log.info("Livraria failed MethodArgumentTypeMismatchException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    /**
     * @param exMethod
     * @param request
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exMethod, WebRequest request) {

        String path = getPath(request);

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : exMethod.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }
        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(CONSTRAINT_VALIDATION_FAILED)
                .description(errors.toString())
                .path(path)
                .build();

        log.info("Livraria failed ConstraintViolationException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());
        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    /**
     * @param exMethod
     * @param request
     * @return
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exMethod, WebRequest request) {


        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(CONSTRAINT_VALIDATION_FAILED)
                .description(exMethod.getCause().getLocalizedMessage())
                .path(request.getDescription(false))
                .build();

        log.info("Livraria failed DataIntegrityViolationException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());
        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    /**
     * @param exMethod
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationError(MethodArgumentNotValidException exMethod, WebRequest request) {

        String path = getPath(request);
        BindingResult bindingResult = exMethod.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        var message = fieldErrors.stream().findFirst().get().getDefaultMessage();

        List<String> fieldErrorDtos = fieldErrors.stream()
                .map(f -> "{'".concat(f.getField()).concat("':'").concat(f.getDefaultMessage()).concat("'}")).map(String::new)
                .collect(Collectors.toList());

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .description(fieldErrorDtos.toString())
                .message(CONSTRAINT_VALIDATION_FAILED)
                .path(path)
                .build();


        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    /**
     * @param exMethod
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> validationError(HttpMessageNotReadableException exMethod, WebRequest request) {

        String path = getPath(request);
        String message = exMethod.getMostSpecificCause().getMessage();

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(CONSTRAINT_VALIDATION_FAILED)
                .description(message)
                .path(path)
                .build();


        log.info("Livraria failed HttpMessageNotReadableException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException, WebRequest request) {

        String path = getPath(request);

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(MensagemErro.ILLEGAL_ARGUMENT_EXCEPTION.getMessage())
                .description(ExceptionResolver.getRootException(illegalArgumentException))
                .path(path)
                .build();


        log.info("Livraria failed IllegalArgumentException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, WebRequest request) {

        String path = getPath(request);

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(MensagemErro.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getStatus())
                .message(MensagemErro.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getMessage())
                .description(ExceptionResolver.getRootException(httpMediaTypeNotSupportedException))
                .path(path)
                .build();


        log.info("Livraria failed httpMediaTypeNotSupportedException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, WebRequest request) {

        String path = getPath(request);

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(MensagemErro.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getStatus())
                .message(MensagemErro.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getMessage())
                .description(ExceptionResolver.getRootException(httpRequestMethodNotSupportedException))
                .path(path)
                .build();


        log.info("Livraria failed httpRequestMethodNotSupportedException httpStatusCode={}, message={}, description={}", ex.getHttpStatusCode().toString(), ex.getMessage(), ex.getDescription());

        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.getOnlyBody());
    }

    private static String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
