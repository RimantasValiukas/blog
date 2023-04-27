package lt.code.academy.blog.controller;

import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/create")
    public String openArticleForm(Model model) {
        model.addAttribute("article", new Article());

        return "form/article";
    }



}
