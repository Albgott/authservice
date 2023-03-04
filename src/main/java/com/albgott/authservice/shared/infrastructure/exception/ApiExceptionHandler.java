package com.albgott.authservice.shared.infrastructure.exception;

import com.albgott.authservice.shared.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            WrongFormatException.class,
            BadParamException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, DomainException exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            PackageException.class
    })
    @ResponseBody
    public List<String> badRequest(HttpServletRequest request, PackageException exception) {
        return exception.errors();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {
            ForbiddenException.class
    })
    @ResponseBody
    public ErrorMessage forbidden(HttpServletRequest request, DomainException exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {
            UnauthorizedException.class
    })
    @ResponseBody
    public ErrorMessage unauthorized(HttpServletRequest request, DomainException exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            NotFoundExeption.class
    })
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, DomainException exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

}
