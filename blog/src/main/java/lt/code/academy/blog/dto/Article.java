package lt.code.academy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.ArticleEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private UUID id;
    private String title;
    private String description;
    private String text;
    private LocalDateTime dateTime;

    public static Article convert(ArticleEntity articleEntity) {
        return new Article(
                articleEntity.getId(),
                articleEntity.getTitle(),
                articleEntity.getDescription(),
                articleEntity.getText(),
                articleEntity.getDateTime()
        );
    }

}
