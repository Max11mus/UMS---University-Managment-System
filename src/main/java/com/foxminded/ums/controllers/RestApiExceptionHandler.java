package com.foxminded.ums.controllers;

import com.foxminded.ums.exeptions.ErrorResponce;
import com.foxminded.ums.exeptions.LectureIlegalUuidException;
import com.foxminded.ums.exeptions.LectureNotFoundException;
import com.foxminded.ums.exeptions.StudentIlegalUuidException;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.exeptions.TeacherIlegalUuidException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import com.foxminded.ums.exeptions.TimeTableIlegalTeacherUuidException;
import com.foxminded.ums.exeptions.TimeTableUnitIlegalStudentUuidException;
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

    @ExceptionHandler(value = {LectureIlegalUuidException.class,
            StudentIlegalUuidException.class,
            TeacherIlegalUuidException.class,
            TimeTableUnitIlegalStudentUuidException.class,
            TimeTableIlegalTeacherUuidException.class})
    public ResponseEntity<ErrorResponce> handleBadRequest(RuntimeException e) {
        ErrorResponce errorResponce = new ErrorResponce(e.getMessage());

        LOGGER.error(e.getMessage(), e.getCause());

        return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {LectureNotFoundException.class,
            StudentNotFoundException.class,
            TeacherNotFoundException.class})
    public ResponseEntity<ErrorResponce> handleNotFound(RuntimeException e) {
        ErrorResponce errorResponce = new ErrorResponce(e.getMessage());

        LOGGER.error(e.getMessage(), e.getCause());

        return new ResponseEntity<ErrorResponce>(errorResponce, HttpStatus.NOT_FOUND);
    }

}
