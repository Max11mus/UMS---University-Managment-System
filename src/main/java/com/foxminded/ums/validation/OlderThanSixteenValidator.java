package com.foxminded.ums.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class OlderThanSixteenValidator implements ConstraintValidator<OlderThanSixteen, LocalDate> {
    @Autowired
    private ClockBean clockBean;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        int currentYear = LocalDate.now(clockBean.getClock())
                .getYear();
        int birthYear = value.getYear();

        if ((currentYear - birthYear) < 16) {
            return false;
        }

        return true;
    }
}
