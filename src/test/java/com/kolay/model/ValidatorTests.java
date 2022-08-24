package com.kolay.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;
import java.util.Set;

public class ValidatorTests {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean =
                new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Test
    void shouldNotValidateWhenTitleEmpty() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        Kpac kpac = new Kpac();
        kpac.setTitle("");
        kpac.setDescription("description");

        Validator validator = createValidator();
        Set<ConstraintViolation<Kpac>> constraintViolations = validator.validate(kpac);

        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<Kpac> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("title");
        assertThat(violation.getMessage()).isEqualTo("must not be empty");
    }
}
