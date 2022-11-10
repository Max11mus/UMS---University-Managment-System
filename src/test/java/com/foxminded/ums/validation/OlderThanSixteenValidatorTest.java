package com.foxminded.ums.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootTest(classes = ConfigurationForValidatorsTest.class)
class OlderThanSixteenValidatorTest {

    @MockBean
    ConstraintValidatorContext constraintValidatorContext;

    @Autowired
    OlderThanSixteenValidator olderThanSixteenValidator;

    @SpyBean
    private ClockBean clockBean;

    @BeforeEach
    void setFixedClockForTests() {
        String fixedTestInstantTime = "2022-11-11T11:11:11Z";
        String fixedTestZone = "Etc/UTC";
        final Clock clock = Clock.fixed(Instant.parse(fixedTestInstantTime), ZoneId.of(fixedTestZone));
        clockBean.setClock(clock);
    }

    @Test
    void isValid_mustReturnFalse_ifAgeLowerThenSixteenYears(){ //age = fixedTestInstantTime.Year - birthLocalDate.Year
        //given
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = "2010-11-11";
        LocalDate birthLocalDate = LocalDate.parse(birthDate, formatter);

        //when
        boolean result = olderThanSixteenValidator.isValid(birthLocalDate, constraintValidatorContext);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    void isValid_mustReturnTrue_ifAgeNotLowerSixteenYears(){ //age = fixedTestInstantTime.Year - birthLocalDate.Year
        //given
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = "2006-12-12";
        LocalDate birthLocalDate = LocalDate.parse(birthDate, formatter);

        //when
        boolean result = olderThanSixteenValidator.isValid(birthLocalDate, constraintValidatorContext);

        //then
        Assertions.assertTrue(result);

    }
}