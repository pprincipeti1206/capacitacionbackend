package com.incloud.hcp.annotation;

import com.incloud.hcp.utils.Constants;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = Constants.PATTERN, flags = Pattern.Flag.CASE_INSENSITIVE)
@ReportAsSingleViolation
public @interface ExtendedEmailValidator {

    String message() default "{message.email.invalido}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
