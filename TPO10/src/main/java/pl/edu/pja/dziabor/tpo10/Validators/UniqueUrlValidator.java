package pl.edu.pja.dziabor.tpo10.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import pl.edu.pja.dziabor.tpo10.Services.LinkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UniqueUrlValidator implements ConstraintValidator <IsUnique, String> {
    @Autowired
    private MessageSource messageSource;
    Locale currentLocale = LocaleContextHolder.getLocale();
    @Autowired
    private LinkService linkService;

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {

        boolean unique = linkService.checkUrlUnique(url);
        if (unique) {
            return true;
        }


        addConstraintViolation(context, "url.notUnique");

        return false;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String messageKey) {
        context.buildConstraintViolationWithTemplate("{" + messageKey + "}")
                .addConstraintViolation();

    }
}