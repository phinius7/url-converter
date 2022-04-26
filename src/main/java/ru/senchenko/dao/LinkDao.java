package ru.senchenko.dao;

public class LinkDao {

    private String originalLink = "";
    private String shortLink = "";

    public LinkDao() {}

    public LinkDao(String originalLink, String shortLink) {
        this.originalLink = originalLink;
        this.shortLink = shortLink;
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
}
