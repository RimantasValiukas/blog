package lt.code.academy.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ArticleNotExistRuntimeException extends RuntimeException {
    private final UUID id;
}
