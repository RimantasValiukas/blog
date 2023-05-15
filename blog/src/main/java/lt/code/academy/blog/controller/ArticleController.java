package lt.code.academy.blog.controller;

import jakarta.validation.Valid;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.service.ArticleService;
import lt.code.academy.blog.service.CommentService;
import lt.code.academy.blog.service.MessageService;
import lt.code.academy.blog.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final MessageService messageService;
    private final CommentService commentService;
    private final UserService userService;

    public ArticleController(ArticleService articleService, MessageService messageService, CommentService commentService, UserService userService) {
        this.articleService = articleService;
        this.messageService = messageService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String openArticleForm(Model model, String messageKey) {
        model.addAttribute("article", new Article());
        model.addAttribute("message", messageService.getMessage(messageKey));

        return "form/article";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String uploadArticle(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/article";
        }

        String messageKey = "lt.code.academy.blog.article.create.succes.message";
        articleService.createArticle(article);

        return "redirect:/articles/create?messageKey=" + messageKey;
    }

    @GetMapping
    public String showAllArticles(Model model, @PageableDefault(sort = {"dateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("articles", articleService.getArticlesByPage(pageable));

        return "articles";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/update")
    public String openArticleForm(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));

        return "form/article";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/update")
    public String updateArticle(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/article";
        }
        articleService.updateArticle(article);

        return "redirect:/articles/readArticle/" + article.getId();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable UUID id, Model model) {
        articleService.deleteArticle(id);
        model.addAttribute("article", articleService.getAllArticles());

        return "redirect:/articles";
    }

    @GetMapping("/readArticle/{id}")
    public String readArticle(Model model, @PathVariable UUID id) {
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("comments", commentService.getCommentsByArticleId(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("users", userService.getAllUsers());

        return "readArticle";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/readArticle/{articleId}")
    public String createComment(@Valid Comment comment, @PathVariable UUID articleId, @RequestParam(value = "userId", required = false) UUID userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "readArticle";
        }
        comment.setUserId(userId);
        comment.setArticleId(articleId);
        commentService.saveComment(comment);

        return "redirect:" + articleId;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/readArticle/{articleId}/{commentId}/delete")
    public String deleteComment(Model model, @PathVariable UUID articleId, @PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        model.addAttribute("comments", commentService.getCommentsByArticleId(articleId));

        return "redirect:/articles/readArticle/" + articleId;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/readArticle/{articleId}/{commentId}/update")
    public String openUpdateCommentForm(Model model, @PathVariable UUID articleId, @PathVariable UUID commentId) {
        model.addAttribute("article", articleService.getArticle(articleId));
        model.addAttribute("comment", commentService.getCommentById(commentId));

        return "form/comment";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/readArticle/{articleId}/{commentId}/update")
    public String updateComment(@Valid Comment comment, @PathVariable UUID articleId, @PathVariable UUID commentId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form/comment";
        }

        comment.setId(commentId);
        commentService.saveComment(comment);

        return "redirect:/articles/readArticle/" + articleId;
    }

}
