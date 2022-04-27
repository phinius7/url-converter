package ru.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.senchenko.dao.UrlDao;
import ru.senchenko.services.UrlService;

import javax.validation.Valid;

@Controller
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("linkForShort", new UrlDao());
        model.addAttribute("linkForOriginal", new UrlDao());
        return "index";
    }

    @PostMapping("/short")
    public String getShortUrl(Model model, @Valid UrlDao urlDao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("linkForShort", new UrlDao());
            model.addAttribute("linkForOriginal", new UrlDao());
            model.addAttribute("error", true);
            return "index";
        }
        UrlDao processedLink = urlService.generateShortUrl(urlDao);
        model.addAttribute("linkForShort", processedLink);
        model.addAttribute("linkForOriginal", new UrlDao());
        return "index";
    }

    @PostMapping("/original")
    public String getOriginalUrl(Model model, UrlDao urlDao) {
        UrlDao processedLink = urlService.getOriginalUrl(urlDao);
        model.addAttribute("linkForShort", new UrlDao());
        if (processedLink.getLength() == -1) {
            model.addAttribute("linkForOriginal", new UrlDao());
            model.addAttribute("notFound", true);
        } else {
            model.addAttribute("linkForOriginal", processedLink);
        }

        return "index";
    }
}
