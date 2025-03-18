package pl.edu.pja.dziabor.tpo10.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Autowired
    private MessageSource messageSource;  // Inject MessageSource
    Locale currentLocale = LocaleContextHolder.getLocale();
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        List<String> errors = new ArrayList<>();
        if(password.isEmpty()){
            return true;
        }
        if (password.length() < 11) {
            errors.add("password.tooShort");
        }
        if (!password.matches(".*[a-z].*")) {
            errors.add("password.missingLowercase");
        }
        if (!password.matches("(?:.*[A-Z].*){2}")) {
            errors.add("password.missingUppercase");
        }
        if (!password.matches("(?:.*\\d.*){3}")) {
            errors.add("password.missingDigits");
        }
        if (!password.matches("(?:.*[!@#$%^&*()_+=<>?].*){4}")) {
            errors.add("password.missingSpecial");
        }


        if (!errors.isEmpty()) {
            context.disableDefaultConstraintViolation();
            StringBuilder errorString = new StringBuilder("Password: ");
            for (String error : errors) {
                String errorMessage = messageSource.getMessage(error, null, currentLocale);
                errorString.append(errorMessage).append(", ");
            }
            addConstraintViolation(context, errorString.toString());

            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String messageKey) {
        context.buildConstraintViolationWithTemplate("{" + messageKey + "}")
                .addConstraintViolation();
    }
}
