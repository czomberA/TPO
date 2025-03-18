package pl.edu.pja.dziabor.tpo_05;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @PostMapping("/calc")
    @ResponseBody
    public String Convert(@RequestParam String val, @RequestParam int currentSystem, @RequestParam int outputSystem){
        if (currentSystem < 2 || currentSystem > 100 || outputSystem < 2 || outputSystem > 100){
            return "<h1>Invalid input</h1>";
        }
        try {
            return String.format("<h1>%s converted from base of %d to base of %d: %s</h1> <br /> BIN: %s<br />OCT: %s<br />DEC:%s<br />HEX:%s", val, currentSystem,outputSystem, Integer.toString(Integer.parseInt(val, currentSystem), outputSystem), Integer.toString(Integer.parseInt(val, currentSystem), 2), Integer.toString(Integer.parseInt(val, currentSystem), 8), Integer.toString(Integer.parseInt(val, currentSystem), 10), Integer.toString(Integer.parseInt(val, currentSystem), 16));
        } catch (Exception e) {
            return "<h1>Error</h1>";
        }
    }

}
