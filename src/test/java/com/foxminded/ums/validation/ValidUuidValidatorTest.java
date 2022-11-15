package com.foxminded.ums.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.validation.ConstraintValidatorContext;

@SpringBootTest(classes = ConfigurationForValidatorsTest.class)
class ValidUuidValidatorTest {

    @MockBean
    ConstraintValidatorContext constraintValidatorContext;

    @Autowired
    UUIDValidator UUIDValidator;

    @SpyBean
    private ClockBean clockBean;

    @Test
    void isValid_mustReturnFalse_ifInCorrectUuid(){
        //given
        String incorrectUuid = "QQQQ-8888-SSSS-9999-OOOO";

        //when
        boolean result = UUIDValidator.isValid(incorrectUuid, constraintValidatorContext);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    void isValid_mustReturnTrue_ifCorrectUuid(){
        //given
        String correctUuid = "6c8a1efe-6367-11ed-81ce-0242ac120002";

        //when
        boolean result = UUIDValidator.isValid(correctUuid, constraintValidatorContext);

        //then
        Assertions.assertTrue(result);

    }
}