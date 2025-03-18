package pl.edu.pja.dziabor.tpo10.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@RestController
public class LocaleController {

    private final LocaleResolver localeResolver;

    public LocaleController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping("/changeLocale")
    public String changeLocale(@RequestParam("locale") String locale, HttpServletRequest request, HttpServletResponse response) {
        Locale newLocale = new Locale(locale);
        localeResolver.setLocale(request, response, newLocale);

        return "<a href=/>Return</a>";
    }
}
