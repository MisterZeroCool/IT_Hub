package ru.alexander.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.alexander.controller.repr.ArticleRepr;
import ru.alexander.error.NotFoundException;
import ru.alexander.service.ArticleService;


@Controller
public class ArticlesController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    private final ArticleService articleService;



    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String adminArticlesPage(Model model) {
        model.addAttribute("activePage", "Articles");
        model.addAttribute("articles", articleService.findAll());
        return "articles";
    }

    @GetMapping("/article/{id}/edit")
    public String adminEditArticle(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Articles");
        model.addAttribute("article", articleService.findById(id).orElseThrow(NotFoundException::new));
        return "article_form";
    }

    @DeleteMapping("/article/{id}/delete")
    public String adminDeleteArticle(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Articles");
        articleService.deleteById(id);
        return "redirect:/articles";
    }

    @GetMapping("/article/create")
    public String adminCreateArticle(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Articles");
        model.addAttribute("article", new ArticleRepr());
        return "article_form";
    }

    @PostMapping("/article")
    public String adminUpsertArticle(Model model, RedirectAttributes redirectAttributes, ArticleRepr article) {
        model.addAttribute("activePage", "Articles");

        try {
            articleService.save(article);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating article", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (article.getId() == null) {
                return "redirect:/article/create";
            }
            return "redirect:/article/" + article.getId() + "/edit";
        }
        return "redirect:/articles";
    }
}
