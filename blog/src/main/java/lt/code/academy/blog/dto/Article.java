package lt.code.academy.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 5, max = 255)
    private String title;
    @NotBlank
    @Size(min = 5, max = 500)
    private String description;
    @NotBlank
    @Size(min = 5, max = 5000)
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
