package pl.edu.pja.dziabor.tpo10.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NameValidator implements ConstraintValidator <ValidName, String> {
    @Autowired
    private MessageSource messageSource;
    Locale currentLocale = LocaleContextHolder.getLocale();

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if (name.isEmpty()) {
            return true;
        }
        if (name.length() < 5 || name.length() > 20) {
            return false;
        }

        addConstraintViolation(context, "name.badLength");

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String messageKey) {
        context.buildConstraintViolationWithTemplate("{" + messageKey + "}")
                .addConstraintViolation();

    }
}