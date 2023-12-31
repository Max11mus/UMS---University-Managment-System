package com.foxminded.ums.logging;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.exeptions.LectureNotFoundException;
import com.foxminded.ums.exeptions.ServerUnavailableException;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@Aspect
public class TransactionsAspectLogger {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionsAspectLogger.class);

    @Pointcut("within(com.foxminded.ums.service.*)")
    public void anyTransaction(){
    }

    @Around("anyTransaction()")
    public Object logTransaction(ProceedingJoinPoint point) throws Throwable {
        LOGGER.info("Start Transaction");

        LOGGER.info("Method: ");

        String signature = point.getSignature().toLongString();
        LOGGER.info(signature);

        LOGGER.info("Arguments: ");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            LOGGER.info(arg.toString());
        }

        Object result = null;
        try {
            result = point.proceed();
            LOGGER.info("Result: ");

            if (result != null) {
                LOGGER.info(result.toString());
            } else {
                LOGGER.info("void");
            }

            LOGGER.info("End Transaction");
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("Unroll Transaction");
            if (e instanceof NoSuchElementException || e instanceof EmptyResultDataAccessException) {

                if (signature.contains("deleteStudent")) {
                    throw new StudentNotFoundException((UUID) args[0], e);
                }
                if (signature.contains("deleteTeacher")) {
                    throw new TeacherNotFoundException((UUID) args[0], e);
                }

                if (signature.contains("findTeacher")) {
                    throw new TeacherNotFoundException((UUID) args[0], e);
                }

                if (signature.contains("updateTeacher")) {
                    throw new TeacherNotFoundException(((TeacherDto) args[0]).getId(), e);
                }

                if (signature.contains("findLecture")) {
                    throw new LectureNotFoundException((UUID) args[0], e);
                }

                if (signature.contains("findStudent")) {
                    throw new StudentNotFoundException((UUID) args[0], e);
                }

                if (signature.contains("updateStudent")) {
                    throw new TeacherNotFoundException(((StudentDto) args[0]).getId(), e);
                }

            } else {
                throw new ServerUnavailableException(e.getMessage(), e);
            }
        }
        return result;
    }

}
