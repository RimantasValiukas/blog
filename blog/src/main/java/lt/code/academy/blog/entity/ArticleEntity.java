package lt.code.academy.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.dto.Article;


import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(nullable = false, length = 5000)
    private String text;
    @Column(nullable = false)
    private LocalDateTime dateTime;

    public static ArticleEntity convert(Article article) {
        return new ArticleEntity(
          article.getId(),
          article.getTitle(),
          article.getDescription(),
          article.getText(),
          LocalDateTime.now()
        );
    }
}
