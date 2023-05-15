package lt.code.academy.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    private UUID id;
    private UUID articleId;
    private String commentText;
    private LocalDateTime dateTime;
    private UUID userId;

    public static Comment convert(CommentEntity entity) {
        return new Comment(
                entity.getId(),
                entity.getArticleId(),
                entity.getCommentText(),
                entity.getDateTime(),
                entity.getUserId()
        );
    }
}
