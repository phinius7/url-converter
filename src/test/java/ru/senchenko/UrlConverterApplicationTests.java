package ru.senchenko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.senchenko.dao.UrlDao;
import ru.senchenko.services.UrlService;

@SpringBootTest
@AutoConfigureMockMvc
class UrlConverterApplicationTests {

    private static final String TOO_LONG_URL = "https://2048.test/tMnWWAvM1hTHTQIlOShi99H3kVjy0leKR6XZGlMcwG9QeNaF7YQ3s" +
            "w16p1kUkjCtWaPocsL35VJq9qH9dnT2BhCMP5TJ5LAa3favzDX64mVZhvcOXENlsBKvhtMYVnIPHikXKal5w2xy9CVi1yIvDbCQqn8D881" +
            "dgDJ9YFOKDm0U9FF6T2p2Rwk1ZWCsXPktbARBPFOJm7nWnz038mgntQZoN1nkCjofJlCUXAKDeAYK2zWIITGlf5q9FnyUVcamr3PDDnuV0" +
            "xTMROTKO1BuQP6hGVc85wEqHmwP926qZh2SMQPPbI7QD6BYNXao2nqlfkpc3oXelbdzjjMKCkWMOKjr1mWr0k9p8PbALrNr7mG7JtDhWP4" +
            "jtwnKngeA7fZUND8iweBoEOF6FKz7V5ZYf35DxBGMrWBB9ltSSe5F1qYhW0lmw5WZsRLdNOt4LqcGrCkLsxyiut5Udv95rfhrLH7Yxr8PC" +
            "FQEFGV8xIKvx2AOHeTD4iKkbYK8QqudjSFI1vldCyjO2MUrL99VBXyQWWCxN4U3ac7you3cFHzOZSWSFVI3gP7fImRuuSpbIUtgwt0c72T" +
            "cVChcLSFGCLYUB4ugjacgKNrGgIXLKptGH6iw1KTYo9VmuHbchkgRmtExqe9pQSKhfgfhwi6LLrwMCYmPFDq2rGC6ei7HQtQBEoB3IA82A" +
            "w2FV8gyVt9AiLMKyc2GMHRJiA04akiv94qqbn2RuqxnxwvKdMo9X49EvsowQtGhQCfv5WufPYJuh9jejeqVN89ypdVhwXtKUAWm5Czghrh" +
            "FAwfyQ54Sd5OTPt8MVJqGrPh7C88Jcoy5Wre7U0cRS3FRBK1NHOua1M1zFviawFQ9Az22Smuc3qaUjwYws6DX6j6xanXnfnWpgCB4Bs4vp" +
            "c6z8l1RGyt7OPj5wMLAkMbXxHpdqx8S4XGZ983FlTBnppgIF7Ws2bvJSgFzhlHzVmsr0ErGFbJqhJ8qy0Gw6Q0HH5Z8QDaleIwqQrHwd4c" +
            "xXixZ7GLtPWsAJn2St82Lxr3ug7YhxFmTRE1J805HPgH2EEekxGADvUUo3yBfnBNIt81v4N3RD4Q25ZBJtheXUEXQ3HEIEYXu5FjA8I1hC" +
            "XcMPM3P2vW1rZtJ0T3dcsaq9dOJEosW4NhpIjUQhJJz7rDGmsmgl9oPsbMKinKNM7D8XHCVaS4XB89ePtjviKtgKxNUwtJlTW7ecTMIDPw" +
            "xyGoAqUmcR1fKikyrppp0vao5RSHhASsSQZjZz7Cc7S7X7oXZEur5KGP2XkizP7zY6WtXr4y9VESSbKjAbr2kM9PbsgMJ10EIouwTnlmg8" +
            "uS37d5vGnHsgPJVlJ0zJkcfNdOV0IBZuWIB2yO4fjVwhRwTiVmjgzHFkyKlIiVnWzw0kMUotBPm1K7YZha7dHPiteiabM3ox2e2Aw552zA" +
            "sBCP2KYUPF0AsOEUXPAmmUp4J4Kz4qnsJKmsqqhJKykz0nMnmjZvMjNrlie6gp7axDQbUrdCfSpe7MNCkOCnQoYEVMLdPeA6aigch9pc2M" +
            "iO3G2vwzX02Vf89S1iBiTRYt305MOixEf4OoRw9AuCY1vEzCbdnPM7syBp0JIzAEMoagshVyMwDuR9BIqbh4yRYy8OWRD2NzBCdSSj3vCn" +
            "UAPYbTfKtshp4o5zpYHnVtz7L1gmb749ey8tHsQ3x8Ft9xy1ufJExzdzWxeBAfR78FJGKN5fYnA2APpNOL4wH10N7diAiYvlTBRXBCZlgq" +
            "WEcQZHEo2vmSrlofqrIUKm9r65JicMc67Qh87zgsuHjgINjQqZxlNlOI5FoqAibWZIrl32aS9KJJlGG2JIbio0C2nFjQbzOAG9uhA4jeWL" +
            "VrLtjKc3NNNRCHezC4ljYxkoBiYv1LGrrHSMsQDVddTdni2Gct10P6T2nQyLnGfG47SinBXeKvdcLUBpRgrKCa6EvewHpCqYOEzquEVsEG" +
            "F2vVnPxJz2b0abeMdffWuIatsUYMgLHnfKHLiJc4ZXVYKLy7qpoQ9WJR7b8vOioTYEGT2";

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

    @Test
    void checkProcessOnLongUrl() {
        UrlDao urlDao = new UrlDao(TOO_LONG_URL, 2);
        UrlDao shortUrl = urlService.generateShortUrl(urlDao);
        UrlDao originalUrl = urlService.getOriginalUrl(shortUrl);
        Assertions.assertEquals(urlDao.getOriginalLink(), originalUrl.getOriginalLink());
    }

    @Test
    void checkErrorOnLongUrl() {
        UrlDao urlDao = new UrlDao(TOO_LONG_URL + "o", 2);
        try {
            urlService.generateShortUrl(urlDao);
            Assertions.fail("Expected DataIntegrityViolationException");
        } catch (DataIntegrityViolationException e) {
            Assertions.assertNotEquals("", e.getMessage());
        }
    }
}
