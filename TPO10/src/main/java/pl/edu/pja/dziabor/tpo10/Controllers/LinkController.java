package pl.edu.pja.dziabor.tpo10.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import org.hibernate.annotations.Checks;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.dziabor.tpo10.Models.Link;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTO;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTOpatch;
import pl.edu.pja.dziabor.tpo10.Models.LinkDtoNew;
import pl.edu.pja.dziabor.tpo10.Services.LinkService;
import org.springframework.ui.Model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;


@Controller
public class LinkController {
    private final LinkService linkService;
    


    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("Link", new LinkDtoNew());
        return "index";
    }


    @PostMapping("/save")
    public String register(@Valid @ModelAttribute("Link") LinkDtoNew linkDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "index";

        LinkDTO link = linkService.prepareAndSaveLink(linkDTO);
        return "redirect:link?id="+link.getId();
    }


    @GetMapping("/link")
    public String getLink(@RequestParam String id, Model model) {
        linkService.getLinkById(id).ifPresent(code -> model.addAttribute("link", code));
        return "link";
    }

    @PostMapping("/delete")
    public String deleteLink(@RequestParam String password, @RequestParam String id) {

        if (password == null){
             return "redirect:/customError?error_type=" + URLEncoder.encode("Please enter password", StandardCharsets.UTF_8);
        }
        String response = linkService.deleteLink(id, password);
        switch (response) {
            case "OK":
                return "confirmation";
            case "BAD_PASS":
                return "redirect:/customError?error_type=" + URLEncoder.encode("Wrong password", StandardCharsets.UTF_8);
            case "NO_LINK":
                return "redirect:/customError?error_type=" + URLEncoder.encode("Link doesn't exist", StandardCharsets.UTF_8);
            default:
                return "redirect:/customError?error_type=" + URLEncoder.encode("Something went wrong", StandardCharsets.UTF_8);

        }
    }

    @GetMapping("/edit")
    public String editLink(@RequestParam String id, @RequestParam String password, Model model) {
        if (!linkService.passwordMatch(id, password)) {
            return "redirect:/customError?error_type=" + URLEncoder.encode("Wrong password", StandardCharsets.UTF_8);
        }

        LinkDTO linkDTO = linkService.getLinkById(id).orElseThrow();
        LinkDTOpatch patch = new LinkDTOpatch();
        patch.setId(linkDTO.getId());
        patch.setName(linkDTO.getName());
        patch.setTargetUrl(linkDTO.getTargetUrl());


        model.addAttribute("password", password);
        model.addAttribute("Link", patch);
        return "edit";
    }

    @PostMapping("/saveEdited")
    public String saveEdited(
            @RequestParam String id,
            @RequestParam String password, // Capture the password
            @Valid @ModelAttribute("Link") LinkDTOpatch linkDTOpatch,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("password", password);
            model.addAttribute("Link", linkDTOpatch);
            return "edit";
        }

        try {
            LinkDTO existingLink = linkService.getLinkById(id).orElseThrow(() -> new NoSuchElementException("Link not found"));
            Link withThisUrl = linkService.findLinkByTargetUrl(linkDTOpatch.getTargetUrl());
            if (!withThisUrl.getId().isEmpty() && !existingLink.getId().equals(withThisUrl.getId())) {
                return "redirect:/customError?error_type=" + URLEncoder.encode("Url already exists", StandardCharsets.UTF_8);

            }
            LinkDTO updatedLink = applyPatch(existingLink, linkDTOpatch);
            linkService.updateLink(updatedLink, password);
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "Link not found.");
            return "redirect:/customError?error_type=" + URLEncoder.encode("Link not found", StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "redirect:/customError?error_type=" + URLEncoder.encode("Something went wrong", StandardCharsets.UTF_8);
        }

        return "redirect:/link?id=" + id;
    }


    private LinkDTO applyPatch(LinkDTO original, LinkDTOpatch patch) {
        if (patch.getName() != null && !patch.getName().isBlank()) {
            original.setName(patch.getName());
        }
        if (patch.getTargetUrl() != null && !patch.getTargetUrl().isBlank()) {
            original.setTargetUrl(patch.getTargetUrl());
        }
        return original;
    }

    @GetMapping("/customError")
    public String showError(@RequestParam(name = "error_type", required = false) String errorType, Model model) {
        System.out.println("Received error_type: " + errorType);
        model.addAttribute("error_type", errorType);
        return "customError";
    }



}
