package pl.edu.pja.dziabor.tpo10.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTO;
import pl.edu.pja.dziabor.tpo10.Models.LinkDtoNew;
import pl.edu.pja.dziabor.tpo10.Services.LinkService;
import org.springframework.validation.FieldError;



import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/links",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class LinkApiController {
    private final LinkService linkService;

    public LinkApiController(LinkService linkService) {
        this.linkService = linkService;
    }
    @Tag(name = "POST", description = "Add new link")
    @PostMapping
    public ResponseEntity<LinkDTO> saveLink(@Valid @RequestBody LinkDtoNew linkDTOnew) {
//        if (linkDTOnew.getName() == null || linkDTOnew.getTargetUrl() == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
        LinkDTO savedLink = linkService.prepareAndSaveLink(linkDTOnew);
        URI savedLinkLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedLink.getId()).toUri();
        return ResponseEntity.created(savedLinkLocation).body(savedLink);

    }

    @Tag(name = "GET", description = "Get information about a link")
    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> getLink(@PathVariable String id) {
        return linkService.getLinkById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "PATCH", description = "Update link information")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLink(@PathVariable String id, @RequestBody LinkDtoNew linkDTOnew) {
        if (linkDTOnew.getPassword() == null){
            return ResponseEntity.status(403).header("Reason", "wrong password").build();
        }
        if (!linkService.passwordMatch (id, linkDTOnew)){
            return ResponseEntity.status(403).header("Reason", "wrong password").build();

        }
        try {
           LinkDTO linkDTO = linkService.getLinkById(id).orElseThrow();

               LinkDTO patchedLinkDTO = applyPatch(linkDTO, linkDTOnew);
               linkService.updateLink(patchedLinkDTO, linkDTOnew.getPassword());


        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }

    private LinkDTO applyPatch(LinkDTO linkDTO, LinkDtoNew linkDTOPatch) throws JsonPatchException, JsonProcessingException {
        if (linkDTOPatch.getName() != null && !Objects.equals(linkDTO.getName(), linkDTOPatch.getName())){
            linkDTO.setName(linkDTOPatch.getName());
        }
        if (linkDTOPatch.getTargetUrl() != null && !Objects.equals(linkDTO.getTargetUrl(), linkDTOPatch.getTargetUrl())){
            linkDTO.setTargetUrl(linkDTOPatch.getTargetUrl());
        }
        return linkDTO;
    }

    @Tag(name = "DELETE", description = "Remove link")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLink(@PathVariable String id, @RequestParam(required = false) String password ) {
        if (password == null){
            return ResponseEntity.status(403).header("Reason", "wrong password").build();
        }
        String response = linkService.deleteLink(id, password);
        return switch (response) {
            case "OK" -> ResponseEntity.noContent().build();
            case "BAD_PASS" -> ResponseEntity.status(403).header("Reason", "wrong password").build();
            case "NO_LINK" -> ResponseEntity.status(204).build();
            default -> ResponseEntity.badRequest().build();
        };

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handle(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }



}
