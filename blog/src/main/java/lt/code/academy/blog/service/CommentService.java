package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.entity.ArticleEntity;
import lt.code.academy.blog.entity.CommentEntity;
import lt.code.academy.blog.exception.ArticleNotExistRuntimeException;
import lt.code.academy.blog.exception.CommentNotExistRuntimeException;
import lt.code.academy.blog.repository.ArticleRepository;
import lt.code.academy.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public void saveComment(Comment comment) {
        CommentEntity commentEntity = CommentEntity.convert(comment);
        ArticleEntity articleEntity = articleRepository.findById(comment.getArticleId()).orElseThrow(() -> new ArticleNotExistRuntimeException(comment.getArticleId()));
        commentEntity.setArticleEntity(articleEntity);
        commentRepository.save(commentEntity);
    }

    public List<Comment> getCommentsByArticleId(UUID articleId) {
        return commentRepository.findAllByArticleId(articleId)
                .stream()
                .map(Comment::convert)
                .toList();
    }

    public Comment getCommentById(UUID id) {
        return commentRepository.findById(id)
                .map(Comment::convert)
                .orElseThrow(() -> new CommentNotExistRuntimeException(id));
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

}
