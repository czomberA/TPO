package pl.edu.pja.dziabor.tpo10.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import pl.edu.pja.dziabor.tpo10.Validators.IsUnique;
import pl.edu.pja.dziabor.tpo10.Validators.ValidPassword;

public class LinkDtoNew {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @URL(protocol = "https")
    @IsUnique
    private String targetUrl;
    @ValidPassword
    private String password;
    public LinkDtoNew() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
