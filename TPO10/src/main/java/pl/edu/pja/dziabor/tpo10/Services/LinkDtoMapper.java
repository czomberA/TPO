package pl.edu.pja.dziabor.tpo10.Services;

import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo10.Models.Link;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTO;
import pl.edu.pja.dziabor.tpo10.Models.LinkDtoNew;

import java.security.SecureRandom;


@Service
public class LinkDtoMapper {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    public LinkDTO map(Link link){
        LinkDTO dto = new LinkDTO();
        dto.setId(link.getId());
        dto.setName(link.getName());
        dto.setVisits(link.getVisits());
        dto.setRedirectUrl(link.getRedirectUrl());
        dto.setTargetUrl(link.getTargetUrl());
        return dto;
    }

    public Link map(LinkDTO dto){
        Link link = new Link();
        link.setId(dto.getId());
        link.setName(dto.getName());
        link.setVisits(dto.getVisits());
        link.setRedirectUrl(dto.getRedirectUrl());
        link.setTargetUrl(dto.getTargetUrl());
        return link;
    }

    public Link map(LinkDTO dto, String password){
        Link link = new Link();
        link.setId(dto.getId());
        link.setName(dto.getName());
        link.setVisits(dto.getVisits());
        link.setRedirectUrl(dto.getRedirectUrl());
        link.setTargetUrl(dto.getTargetUrl());
        link.setPassword(password);
        return link;
    }


    public LinkDTO map(LinkDtoNew linkDTOnew){
        LinkDTO dto = new LinkDTO();
        dto.setName(linkDTOnew.getName());
        dto.setTargetUrl(linkDTOnew.getTargetUrl());
        dto.setVisits(0);
        String shortURL = generateShortURL();
        dto.setRedirectUrl("http://localhost:8080/api/links/"+shortURL);
        dto.setId(shortURL);
        return dto;
    }


    public static String generateShortURL() {
        StringBuilder shortURL = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = RANDOM.nextInt(CHAR_POOL.length());
            shortURL.append(CHAR_POOL.charAt(randomIndex));
        }
        return shortURL.toString();
    }

}
