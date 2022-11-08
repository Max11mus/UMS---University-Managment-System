package com.foxminded.ums.logging;

import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.exeptions.LectureNotFoundException;
import com.foxminded.ums.exeptions.ServerErrorException;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestApiExceptionHandler.class);

    @ExceptionHandler(value = {LectureNotFoundException.class,
            StudentNotFoundException.class,
            TeacherNotFoundException.class})
    public ResponseEntity<ErrorResponce> handleNotFound(RuntimeException e) {
        ErrorResponce errorResponce = new ErrorResponce(HttpStatus.NOT_FOUND, e.getMessage(),
                "The server cannot find the requested resource. In the browser, " +
                        "this means the URL is not recognized. In an API, this can also mean" +
                        " that the endpoint is valid but the resource itself does not exist. " +
                        "Servers may also send this response instead of 403 Forbidden to hide " +
                        "the existence of a resource from an unauthorized client. This response " +
                        "code is probably the most well known due to its frequent occurrence on the web.");

        LOGGER.error(e.getMessage(), e.getCause());

        return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    public ResponseEntity<ErrorResponce> handleServerError(RuntimeException e) {
        ErrorResponce errorResponce = new ErrorResponce(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),
                "The server has encountered a situation it does not know how to handle.");

        LOGGER.error(e.getMessage(), e.getCause());

        return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
