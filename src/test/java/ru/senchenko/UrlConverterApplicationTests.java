package ru.senchenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.senchenko.dao.UrlDao;
import ru.senchenko.services.UrlService;

@SpringBootTest
@AutoConfigureMockMvc
class UrlConverterApplicationTests {

    @Autowired
    UrlService urlService;

    @Test
    void checkValidUrlConvertingTest() {
        UrlDao urlDao = new UrlDao("https://test.test/", 4);
        UrlDao shortUrl = urlService.generateShortUrl(urlDao);
        UrlDao originalUrl = urlService.getOriginalUrl(shortUrl);
        Assertions.assertEquals(urlDao.getOriginalLink(), originalUrl.getOriginalLink());
    }

    @Test
    void checkDeletingUrlTest() throws InterruptedException {
        UrlDao urlDao = new UrlDao("https://test.test/", 1);
        UrlDao shortUrl = urlService.generateShortUrl(urlDao);
        Thread.sleep(120 * 1000L);
        UrlDao originalUrl = urlService.getOriginalUrl(shortUrl);
        Assertions.assertEquals(-1, originalUrl.getLength());
    }

}
