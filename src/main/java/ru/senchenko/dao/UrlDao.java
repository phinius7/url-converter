package ru.senchenko.dao;

import ru.senchenko.entities.UrlEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UrlDao {

    private static final String URL_PATTERN = "^(https?://)?([\\w.]+)\\.([a-z]{2,6}\\.?)(/[\\w\\-.]*)*/?$";

    private Integer id;

    @Size(min = 8, max = 2048)
    @Pattern(regexp = URL_PATTERN)
    private String originalLink;
    @Size(min = 3)
    private String shortLink;

    private Integer length;

    public UrlDao() {}

    public UrlDao(String originalLink, Integer length) {
        this.originalLink = originalLink;
        this.length = length;
    }

    public UrlDao(UrlEntity urlEntity) {
        this.id = urlEntity.getId();
        this.originalLink = urlEntity.getOriginalUrl();
        this.shortLink = urlEntity.getShortUrl();
        this.length = urlEntity.getLength();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
