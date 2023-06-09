package lt.code.academy.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.dto.Comment;


import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;
    @Column(nullable = false, updatable = false, insertable = false)
    private UUID articleId;
    @Column(nullable = false, length = 1000)
    private String commentText;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(updatable = false)
    private UUID userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private ArticleEntity articleEntity;


    public static CommentEntity convert(Comment comment) {
        return new CommentEntity(
                comment.getId(),
                comment.getArticleId(),
                comment.getCommentText(),
                LocalDateTime.now(),
                comment.getUserId(),
                null
        );
    }
}
