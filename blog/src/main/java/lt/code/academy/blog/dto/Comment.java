package lt.code.academy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private UUID id;
    private UUID articleId;
    private String comment;
    private LocalDateTime dateTime;
    private UUID userId;

    public static Comment convert(CommentEntity entity) {
        return new Comment(
                entity.getId(),
                entity.getArticleId(),
                entity.getComment(),
                entity.getDateTime(),
                null
        );
    }
}
