package bg.tu.varna.informationSystem.annotations.implementations;

import bg.tu.varna.informationSystem.annotations.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<Role, String> {

    private Role annotation;

    @Override
    public void initialize(Role annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        Object[] roles = this.annotation.enumClass().getEnumConstants();

        if (roles != null) {
            for (Object role : roles) {
                if (valueForValidation != null) {
                    if (valueForValidation.equals(role.toString())
                            || (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(role.toString()))) {
                        result = true;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
