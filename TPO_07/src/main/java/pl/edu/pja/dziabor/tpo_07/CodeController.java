package pl.edu.pja.dziabor.tpo_07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Controller
public class CodeController {
    private final CodeService codeService;


    public CodeController(CodeService codeService) {
        this.codeService = codeService;
        if (CodeService.getCodes().isEmpty()){
            codeService.populateCodes();
        }
    }

    @PostMapping("/formatCode")
    public String formatCode(@RequestParam("codeText") String codeText, Model model) {
        try{
        codeText = codeService.formatCode(codeText);
        }
        catch (Exception e) {
            return "redirect:/customError?error_type=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        model.addAttribute("code", new Code());
        model.addAttribute("codeText", codeText);
        return "addCode";
    }


    @GetMapping("/newCode")
    public String newCode(@RequestParam(value = "codeText", defaultValue = "") String codeText, Model model) {
        model.addAttribute("code", new Code());
        model.addAttribute("codeText", codeText);
        try{
            return "addCode";
        } catch (Exception e) {
            return "redirect:/customError?error_type=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

    }


    @GetMapping("/invalidID")
    public String invalidID() {
        return "invalidID";
    }

    @PostMapping("/saveCode")
    public RedirectView addCode(Code code, String codeText) {
        code.setText(codeText);
        code.setExpirationDate(code.getDuration(),code.getUnit());

        try{
        if (codeService.saveCode(code)) {
            return new RedirectView("code?id=" + code.getId(), true, false);
        }
        }catch (Exception e){
            return new RedirectView("customError?error_type="+e, true, false);
        }
        return new RedirectView("customError?error_type="+"A note with the specified ID already exists", true, false);
    }


    @GetMapping("/code")
    public String getCode(@RequestParam String id, Model model) {
        codeService.findById(id).ifPresent(code -> model.addAttribute("code", code));
        return "code";
    }

    @GetMapping("/customError")
    public String showError(@RequestParam(name = "error_type", required = false) String errorType, Model model) {
        System.out.println("Received error_type: " + errorType);
        model.addAttribute("error_type", errorType);
        return "customError";
    }

}
