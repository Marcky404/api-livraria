package io.github.Marcky404.Biblioteca.domain.enums;

import io.github.Marcky404.Biblioteca.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum MensagemErro {

    TELEFONE_INVALIDO("O NUMERO informado não é valido!", HttpStatus.BAD_REQUEST),
    DDD_INVALIDO("DDD informado invalido!", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT_EXCEPTION("Argumento informado não é válido.", HttpStatus.BAD_REQUEST),
    LIVRO_NAO_ENCONTRADO("Livro não encontrado", HttpStatus.NOT_FOUND),
    CLIENTE_NAO_ENCONTRADO("Cliente não encontrado", HttpStatus.NOT_FOUND),
    TELEFONE_NAO_ENCONTRADO("Telefone não encontrado", HttpStatus.NOT_FOUND),
    GENERO_NAO_ENCONTRADO("Gênero não encontrado", HttpStatus.NOT_FOUND),

    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("Media type informado não é suportado", HttpStatus.BAD_REQUEST),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("Metodo HTTP não suportado", HttpStatus.BAD_REQUEST);

    private String message;

    private HttpStatus statusCode;

    private MensagemErro(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public final String getMessage() {
        return this.message;
    }

    protected void setMessage(String value) {
        this.message = value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    protected void setStatus(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus()).message(this.getMessage()).build();
    }

    public BusinessException asBusinessException(String... strings) {
        return BusinessException.builder().httpStatusCode(this.getStatus()).message(this.formatMessage(strings)).build();
    }

    public BusinessException asBusinessException(Object object, String... strings) {
        return BusinessException.builder().httpStatusCode(this.getStatus()).message(this.formatMessage(strings)).object(object).build();
    }

    public String formatMessage(String... strings) {
        return String.format(this.getMessage(), strings);
    }
}
