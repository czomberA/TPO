package pl.edu.pja.dziabor.tpo_09;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        path = "/api/v1",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_PLAIN_VALUE
        }
        )
public class APIController {
        private final PersonService personService;

    public APIController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/BMI")
    ResponseEntity<?> getBMI(@RequestParam("weight") double weight, @RequestParam("height") double height,  @RequestHeader(value = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept) {
        if (height <= 0 || weight <= 0) {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Reason", "Invalid data, weight and height parameters must be positive numbers");
            return ResponseEntity.badRequest().headers(headers).build();
        }

        if (accept.equals(MediaType.TEXT_PLAIN_VALUE)) {
            return ResponseEntity.ok(personService.calculateBMI(weight, height).get().getBmi()+"");
        }
        return personService.calculateBMI(weight, height)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }


    @GetMapping("/BMR/{gender}")
    ResponseEntity<?> getBook(@PathVariable String gender, @RequestParam("weight") double weight,@RequestParam("height") double height,  @RequestParam("age") int age, @RequestHeader(value = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept){
        if (!gender.equalsIgnoreCase("man")  && !gender.equalsIgnoreCase("woman")) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Reason", "Invalid gender data");
            return ResponseEntity.badRequest().headers(headers).build();
        }

        if (height <= 0 || weight <= 0 || age <= 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Reason", "invalid data, weight, height and age parameters must be positive numbers");
            return ResponseEntity.status(499).headers(headers).build();
        }

        if (accept.equals(MediaType.TEXT_PLAIN_VALUE)) {
            return ResponseEntity.ok(personService.calculateBMR(gender, weight, height, age).get().getBmr()+"kcal");
        }
        return personService.calculateBMR(gender, weight, height, age)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
