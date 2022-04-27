package ru.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.senchenko.entities.UrlEntity;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity, Integer> {

    @Query("SELECT url FROM UrlEntity url WHERE url.originalUrl = :link AND url.length = :l")
    Optional<UrlEntity> findByOriginalLink(@Param("link") String link, @Param("l") Integer l);

    @Query("SELECT url FROM UrlEntity url WHERE url.shortUrl = :link")
    Optional<UrlEntity> findByShortLink(@Param("link") String link);

    @Modifying
    @Transactional
    @Query("UPDATE UrlEntity url SET url.lifeTime = :time WHERE url.id = :id")
    void updateLifeTimeById(@Param("id") Integer id, @Param("time") Long time);

    @Modifying
    @Transactional
    @Query("DELETE FROM UrlEntity url WHERE url.length = :length AND url.lifeTime < :time")
    void deleteByDeltaTime(@Param("length") Integer length, @Param("time") Long time);
}
