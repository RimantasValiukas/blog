package lt.code.academy.blog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 2, max = 1000)  //kolkas beda
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
