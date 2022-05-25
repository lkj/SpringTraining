package xyz.lokaj.productPlacement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalOrderStateAdvice {
    @ResponseBody
    @ExceptionHandler(IllegalOrderStateException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    String illegalOrderStateHandler(IllegalOrderStateException exception) {
        return exception.getMessage();
    }
}
