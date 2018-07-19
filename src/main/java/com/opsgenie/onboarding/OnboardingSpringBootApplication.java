package com.opsgenie.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.opsgenie.aws")
@ComponentScan("com.opsgenie.onboarding")
public class OnboardingSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingSpringBootApplication.class, args);
    }
}
