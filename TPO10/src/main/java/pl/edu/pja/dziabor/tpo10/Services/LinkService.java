package pl.edu.pja.dziabor.tpo10.Services;

import org.springframework.stereotype.Service;
import pl.edu.pja.dziabor.tpo10.Repositories.LinkRepository;
import pl.edu.pja.dziabor.tpo10.Models.Link;
import pl.edu.pja.dziabor.tpo10.Models.LinkDTO;
import pl.edu.pja.dziabor.tpo10.Models.LinkDtoNew;

import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final LinkDtoMapper linkDtoMapper;
    public LinkService(LinkRepository linkRepository, LinkDtoMapper linkDtoMapper) {
        this.linkRepository = linkRepository;
        this.linkDtoMapper = linkDtoMapper;
    }


    public LinkDTO saveLink(LinkDTO linkDTO) {
        Link link = linkRepository.save(linkDtoMapper.map(linkDTO));
        return linkDtoMapper.map(link);
    }

    public LinkDTO saveLink(LinkDTO linkDTO, String password) {
        Link link = linkRepository.save(linkDtoMapper.map(linkDTO, password));
        return linkDtoMapper.map(link);
    }

    public LinkDTO prepareAndSaveLink(LinkDtoNew linkDTOnew) {
        LinkDTO preparedLink = linkDtoMapper.map(linkDTOnew);
        if (linkDTOnew.getPassword() != null) {
            return saveLink(preparedLink, linkDTOnew.getPassword());
        }
        return saveLink(preparedLink);
    }


    public Optional<LinkDTO> getLinkById(String id) {
        return linkRepository.findById(id).map(linkDtoMapper::map);
    }

    public Optional<LinkDTO> getLinkByIdVisit(String id) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            int visits = link.get().getVisits();
            visits++;
            link.get().setVisits(visits);
            linkRepository.save(link.get());
        }
        return link.map(linkDtoMapper::map);
    }

    public Optional<Link> updateLink(LinkDTO patchedLinkDTO, String password) {
        return Optional.of(linkRepository.save(linkDtoMapper.map(patchedLinkDTO, password)));
    }

    public Optional<Link> updateLink(LinkDTO patchedLinkDTO) {
        return Optional.of(linkRepository.save(linkDtoMapper.map(patchedLinkDTO)));
    }

    public String deleteLink(String id, String password){
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            if (link.get().getPassword().equals(password)) {
                linkRepository.delete(link.get());
                return "OK";
            } else {
                return "BAD_PASS";
            }
        } else {
            return "NO_LINK";
        }
    }


    public boolean passwordMatch(String id, LinkDtoNew linkDTOnew) {
        if (linkRepository.findById(id).isPresent()) {
            String password = linkRepository.findById(id).get().getPassword();
            return password.equals(linkDTOnew.getPassword());
        }
        return false;
    }


    public boolean passwordMatch(String id, String password) {
        if (linkRepository.findById(id).isPresent()) {
            String passwordTrue = linkRepository.findById(id).get().getPassword();
            return passwordTrue.equals(password);
        }
        return false;
    }

    public boolean checkUrlUnique(String url) {
        return linkRepository.findByTargetUrl(url).isEmpty();
    }

    public Link findLinkByTargetUrl(String url) {
        if (linkRepository.findByTargetUrl(url).isPresent()) {
            return linkRepository.findByTargetUrl(url).get();
        }
        return new Link();
    }
}
