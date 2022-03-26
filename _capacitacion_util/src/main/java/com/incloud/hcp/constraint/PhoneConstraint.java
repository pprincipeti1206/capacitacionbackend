package com.incloud.hcp.constraint;

import com.incloud.hcp.annotation.PhoneValidator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneConstraint implements ConstraintValidator<PhoneValidator, String> {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String pattern;

    @Override
    public void initialize(PhoneValidator contactInfo) {
        pattern = "^([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$";

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        return Pattern.matches(pattern, value);

    }
}
