package pl.edu.pja.dziabor.tpo_06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FakeDataController {

    private final FakeDataService service;

    public FakeDataController(FakeDataService service) {
        this.service = service;
    }

    @RequestMapping("/randomData")
    public String randomData(@RequestParam(defaultValue = "0") int value, Model model,
    @RequestParam(name = "country", required = false) String county,
    @RequestParam(name = "occupation", required = false) String occupation,
    @RequestParam(name = "address", required = false) String address,
    @RequestParam(name = "industry", required = false) String industry,
    @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
    @RequestParam(name = "email", required = false) String email,
    @RequestParam(name = "sport", required = false) String sport,
    @RequestParam(name = "university", required = false) String university,
    @RequestParam(defaultValue = "en", required = false) String language)
    {
        if (value < 0){
            model.addAttribute("error_type", "The number of entries must be > 0");
            return "randomData";
        }
        List <String> additionalFields = new ArrayList<>();
        List<PersonDTO> people;
        if (county != null) {
            additionalFields.add("country");
        }
        if (occupation != null) {
            additionalFields.add("occupation");
        }
        if (address != null) {
            additionalFields.add("address");
        }
        if (industry != null) {
            additionalFields.add("industry");
        }
        if (phoneNumber != null) {
            additionalFields.add("phoneNumber");
        }
        if (email != null) {
            additionalFields.add("email");
        }
        if (sport != null) {
            additionalFields.add("sport");
        }
        if (university != null) {
            additionalFields.add("university");
        }

        if (value !=0 ) {
            try{
            if (additionalFields.isEmpty()){
            people= service.getList(value, language);
            } else {
                people= service.getList(value, additionalFields, language);
            }
           model.addAttribute("people", people); }
            catch (Exception e){
                model.addAttribute("error_type", e.getMessage());
                return "randomData";
            }
        }
        return "randomData";
    }
}
