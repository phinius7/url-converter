package ru.senchenko.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.senchenko.dao.LinkDao;
import ru.senchenko.services.UrlService;

@Controller
@RequestMapping("/")
public class UrlController {
    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("activePage", "index");
        model.addAttribute("link", new LinkDao());
        return "index";
    }

    @PostMapping("/generate")
    public String getResult(Model model, LinkDao linkDao) {
        model.addAttribute("activePage", "generate");
        String longUrl = linkDao.getOriginalLink();
        String shortUrl = linkDao.getShortLink();
        model.addAttribute("link", linkDao);
        return "index";
    }
}
