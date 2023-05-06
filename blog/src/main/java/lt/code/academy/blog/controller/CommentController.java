//package lt.code.academy.blog.controller;
//
//import jakarta.validation.Valid;
//import lt.code.academy.blog.dto.Comment;
//import lt.code.academy.blog.service.CommentService;
//import lt.code.academy.blog.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/readArticle")
//public class CommentController {
//
//    private final CommentService commentService;
//    private final UserService userService;
//
//    public CommentController(CommentService commentService, UserService userService) {
//        this.commentService = commentService;
//        this.userService = userService;
//    }
//
////    @GetMapping("/{articleId}")
////    public String showAllArticleComments(Model model, @PathVariable UUID articleId) {
////        model.addAttribute("comments", commentService.getCommentsByArticleId(articleId));
////        model.addAttribute("users", userService.getAllUsers());
////        model.addAttribute("comment", new Comment());
////
////        return "readArticle";
////    }
//
//
////    @PostMapping("/{articleId}")
////    public String createComment(@Valid Comment comment, BindingResult result, @PathVariable UUID articleId, Model model) {
////        if (result.hasErrors()) {
////            model.addAttribute("comments", commentService.getCommentsByArticleId(articleId));
////            model.addAttribute("users", userService.getAllUsers());
////            return "readArticle";
////        }
////
////        commentService.createComment(comment);
////
////        return "redirect:/readArticle" + articleId;
////    }
//
//}
