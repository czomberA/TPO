package pl.edu.pja.dziabor.tpo10.Models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import pl.edu.pja.dziabor.tpo10.Validators.IsUnique;
import pl.edu.pja.dziabor.tpo10.Validators.ValidName;
import pl.edu.pja.dziabor.tpo10.Validators.ValidPassword;

public class LinkDTOpatch {
    private String id;
    @ValidName
    private String name;
    @URL(protocol = "https")
    private String targetUrl;
    public LinkDTOpatch() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
