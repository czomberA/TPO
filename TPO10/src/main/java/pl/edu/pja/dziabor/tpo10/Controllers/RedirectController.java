package pl.edu.pja.dziabor.tpo10.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTO;
import pl.edu.pja.dziabor.tpo10.Services.LinkService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path="/red")
public class RedirectController {
    private final LinkService linkService;
    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    //Works in Postman and in browser, Swagger has some issues
    @Tag(name = "GET", description = "Get information about a link")
    @GetMapping("/{id}")
    public ResponseEntity<?> getLink(@PathVariable String id) {
        Optional<LinkDTO> link = linkService.getLinkByIdVisit(id);
        if (link.isPresent()) {
            LinkDTO linkDTO = link.get();
            return ResponseEntity.status(302)
                    .header("Location", linkDTO.getTargetUrl())
                    .build();
        }
        System.out.println("Link not found");
        return ResponseEntity.notFound().build();

    }
}
