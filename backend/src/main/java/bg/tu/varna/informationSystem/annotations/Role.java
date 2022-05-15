package bg.tu.varna.informationSystem.annotations;

import bg.tu.varna.informationSystem.annotations.implementations.RoleValidator;
import bg.tu.varna.informationSystem.common.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = {RoleValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    public abstract String message() default Messages.INVALID_ROLE;

    public abstract Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

    public abstract Class<? extends java.lang.Enum<?>> enumClass();

    public abstract boolean ignoreCase() default false;
}
