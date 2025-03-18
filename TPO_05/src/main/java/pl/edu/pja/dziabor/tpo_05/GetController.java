package pl.edu.pja.dziabor.tpo_05;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Controller
public class GetController {
    @GetMapping("/current-time")
    @ResponseBody
    public String CurrentTime(@RequestParam(required = false) String utc, @RequestParam(required = false) String format) {
        DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS YYYY/MM/dd");
        String dateTime;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        if (utc != null) {
            try {
                utc = Capitalize(utc);
                zonedDateTime  = zonedDateTime.withZoneSameInstant(ZoneId.of(utc));
            } catch (Exception e) {
                return "<h1>Invalid time zone. Default: " + zonedDateTime.format(defaultFormat)  + "</h1>";
            }
        }

        if (format != null) {
            try {
                DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(format);
                dateTime = zonedDateTime.format(customFormat);
            } catch (Exception e) {
                return "<h1>Invalid format pattern: " + zonedDateTime.format(defaultFormat) + "</h1>";
            }
        } else {
            dateTime = zonedDateTime.format(defaultFormat);
        }
        return "<h1>" + dateTime  + "</h1>";
    }



    private String Capitalize(String utc) {
        String[] parts = utc.split("/");
        StringBuilder capitalized = new StringBuilder();
        capitalized.append(parts[0].substring(0, 1).toUpperCase())
                .append(parts[0].substring(1).toLowerCase());
        capitalized.append("/");
        capitalized.append(parts[1].substring(0, 1).toUpperCase())
                .append(parts[1].substring(1).toLowerCase());
        return capitalized.toString();
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String getYear(){
        return "<h1>Current year: " + LocalDate.now().getYear() + "</h>";
    }
}
