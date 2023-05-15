package lt.code.academy.blog.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lt.code.academy.blog.validator.PasswordValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "{password.constraint.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
