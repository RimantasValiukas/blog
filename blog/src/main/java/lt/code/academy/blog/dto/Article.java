package lt.code.academy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    public Article(UUID id, String title, String description, String text) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.text = text;
//        dateTime = LocalDateTime.now();
//    }
}
