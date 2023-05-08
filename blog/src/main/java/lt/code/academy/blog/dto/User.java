package lt.code.academy.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.UserEntity;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Size(min = 3, max = 50)
    private String surname;
    @NotBlank
    @Size(min = 5, max = 100)
    private String email;
    @NotBlank
    @Size(min = 10, max = 100)
    private String password;
    @NotBlank
    @Size(min = 10, max = 100)
    private String repeatPassword;

    public static User convert(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                null,
                null
        );
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, surname);
    }
}
