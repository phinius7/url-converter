package ru.senchenko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "urls")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "original_url", length = 2048)
    private String originalUrl;

    @Column(name = "short_url", unique = true)
    private String shortUrl;

    @Column(name = "length")
    private Integer length;

    @Column(name = "life_time")
    private Long lifeTime;

    public UrlEntity() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Long getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Long lifeTime) {
        this.lifeTime = lifeTime;
    }
}
