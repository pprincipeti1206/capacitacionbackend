package com.incloud.hcp.annotation;

import com.incloud.hcp.constraint.PhoneConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { PhoneConstraint.class })
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValidator {

    String message() default "{message,phone.invalido}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
