package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.entity.CommentEntity;
import lt.code.academy.blog.exception.CommentNotExistRuntimeException;
import lt.code.academy.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(Comment comment) {
        commentRepository.save(CommentEntity.convert(comment));
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

    public void updateComment(Comment comment) {
        commentRepository.save(CommentEntity.convert(comment));
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

}
