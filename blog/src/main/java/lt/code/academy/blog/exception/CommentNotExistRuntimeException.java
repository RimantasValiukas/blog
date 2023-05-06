package lt.code.academy.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CommentNotExistRuntimeException extends RuntimeException{
    private final UUID id;
}
