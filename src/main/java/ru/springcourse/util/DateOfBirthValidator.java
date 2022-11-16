package ru.springcourse.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, Date> {

    @Override
    public void initialize(DateOfBirth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Date minDate = new Date("01/01/1900");
        return date.after(minDate);
    }
}
