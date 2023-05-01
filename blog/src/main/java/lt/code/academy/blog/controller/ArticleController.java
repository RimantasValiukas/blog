package lt.code.academy.blog.controller;

import jakarta.validation.Valid;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.service.ArticleService;
import lt.code.academy.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final MessageService messageService;

    public ArticleController(ArticleService articleService, MessageService messageService) {
        this.articleService = articleService;
        this.messageService = messageService;
    }

    @GetMapping("/create")
    public String openArticleForm(Model model, String messageKey) {
        model.addAttribute("article", new Article());
        model.addAttribute("message", messageService.getMessage(messageKey));

        return "form/article";
    }

    @PostMapping("/create")
    public String uploadArticle(@Valid Article article) {
        String messageKey = "lt.code.academy.blog.article.creat.succes.message";
        articleService.createArticle(article);
        return "redirect:/articles/create?messageKey=" + messageKey;
    }

    @GetMapping
    public String showAllArticles(Model model) {
        model.addAttribute("article", articleService.getAllArticles());
        return "articles";
    }

}
