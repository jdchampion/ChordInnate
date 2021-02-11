package chordinnate.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"chordinnate.*"})
public class ValidationConfig {

    @Bean
    public Validator validator() {

        // https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-gettingstarted

        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator( "ValidationMessages" )
                        )
                )
                .buildValidatorFactory()
                .getValidator();
    }
}
