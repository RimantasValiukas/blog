package lt.code.academy.blog.controller;

import jakarta.validation.Valid;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.service.ArticleService;
import lt.code.academy.blog.service.CommentService;
import lt.code.academy.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final MessageService messageService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, MessageService messageService, CommentService commentService) {
        this.articleService = articleService;
        this.messageService = messageService;
        this.commentService = commentService;
    }

    @GetMapping("/create")
    public String openArticleForm(Model model, String messageKey) {
        model.addAttribute("article", new Article());
        model.addAttribute("message", messageService.getMessage(messageKey));

        return "form/article";
    }

    @PostMapping("/create")
    public String uploadArticle(Article article) {
        String messageKey = "lt.code.academy.blog.article.create.succes.message";
        articleService.createArticle(article);
        return "redirect:/articles/create?messageKey=" + messageKey;
    }

    @GetMapping
    public String showAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles";
    }

//    @GetMapping("/{id}")
//    public String getArticleDetails(@PathVariable UUID id, Model model) {
//        model.addAttribute("article", articleService.getArticle(id));
//
//        return "articleDetails";
//    }

    @GetMapping("/{id}/update")
    public String openArticleForm(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));

        return "form/article";
    }

    @PostMapping("/{id}/update")
    public String updateArticle(Article article) {
        articleService.updateArticle(article);

        return "redirect:/articles";
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable UUID id, Model model) {
        articleService.deleteArticle(id);
        model.addAttribute("article", articleService.getAllArticles());

        return "redirect:/articles";
    }

//    @GetMapping("/readArticle/{articleId}")
//    public String showAllArticleComments(Model model, @PathVariable UUID articleId) {
//        model.addAttribute("comments", commentService.getCommentsByArticleId(articleId));
//        model.addAttribute("comment", new Comment());
//
//        return "readArticle";
//    }

    @GetMapping("/readArticle/{id}")
    public String readArticle(Model model, @PathVariable UUID id) {
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("comments", commentService.getCommentsByArticleId(id));
        model.addAttribute("comment", new Comment());

        return "readArticle";
    }

    @PostMapping("/readArticle/{articleId}")
    public String createComment(Comment comment, @PathVariable UUID articleId) {
        comment.setArticleId(articleId);
        commentService.createComment(comment);

        return "redirect:" + articleId;
    }

    @GetMapping("/readArticle/{articleId}/{commentId}/delete")
    public String deleteComment(Model model, @PathVariable UUID articleId, @PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        model.addAttribute("comments", commentService.getCommentsByArticleId(articleId));

        return "redirect:/articles/readArticle/" +articleId;
    }

    @GetMapping("/readArticle/{articleId}/{commentId}/update")
    public String openUpdateCommentForm(Model model, @PathVariable UUID articleId, @PathVariable UUID commentId) {
        model.addAttribute("article", articleService.getArticle(articleId));
        model.addAttribute("comment", commentService.getCommentById(commentId));

        return "readArticle";
    }

    @PostMapping("/readArticle/{articleId}/{commentId}/update")
    public String updateComment(Comment comment, @PathVariable UUID articleId, @PathVariable UUID commentId) {
        commentService.updateComment(comment);

        return "redirect:/articles/readArticle/" + articleId;
    }

}
