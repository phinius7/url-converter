package ru.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senchenko.dao.UrlDao;
import ru.senchenko.entities.UrlEntity;
import ru.senchenko.repositories.UrlRepo;
import ru.senchenko.utils.LinkGenerator;

import java.time.Instant;
import java.util.Optional;

@Service
public class UrlService {

    private static final Long DELAY = 100 * 1000L;
    private final UrlRepo urlRepo;

    @Autowired
    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    @Transactional
    void create(UrlDao urlDao) {
        UrlEntity url = new UrlEntity();
        url.setOriginalUrl(urlDao.getOriginalLink());
        url.setShortUrl(urlDao.getShortLink());
        url.setLength(urlDao.getLength());
        url.setLifeTime(Instant.now().toEpochMilli());
        urlRepo.save(url);
    }

    @Transactional
    void updateLifeTime(Integer id) {
        urlRepo.updateLifeTimeById(id, Instant.now().toEpochMilli());
    }

    @Transactional
    void deleteExpiredUrls(Integer length) {
        urlRepo.deleteByDeltaTime(length, Instant.now().toEpochMilli() - (length * DELAY));
    }

    public UrlDao getOriginalUrl(UrlDao shortUrl) {
        if(shortUrl.getLength() != null) {
            deleteExpiredUrls(shortUrl.getLength());
        }
        Optional<UrlDao> tmpLink = getByShortLinkAndLength(shortUrl.getShortLink());
        if (tmpLink.isPresent()) {
            return tmpLink.get();
        } else {
            shortUrl.setLength(-1);
            return shortUrl;
        }
    }

    public UrlDao generateShortUrl(UrlDao url) {
        deleteExpiredUrls(url.getLength());
        Optional<UrlDao> tmpLink = getByOriginalLinkAndLength(url.getOriginalLink(), url.getLength());
        if (tmpLink.isPresent()) {
            updateLifeTime(tmpLink.get().getId());
            return tmpLink.get();
        } else {
            String shortUrl;
            LinkGenerator linkGenerator = new LinkGenerator();
            do {
                shortUrl = linkGenerator.generateLink(url.getLength());
            } while (getByShortLinkAndLength(shortUrl).isPresent());
            url.setShortLink(shortUrl);
            create(url);
        }
        return url;
    }

    private Optional<UrlDao> getByOriginalLinkAndLength(String originalLink, Integer length) {
        return urlRepo.findByOriginalLink(originalLink, length).map(UrlDao::new);
    }

    private Optional<UrlDao> getByShortLinkAndLength(String shortLink) {
        return urlRepo.findByShortLink(shortLink).map(UrlDao::new);
    }
}
