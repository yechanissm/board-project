package com.board.boardproject.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defalutTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
    ) {
        defalutTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        return defalutTemplateResolver;
    }

    @RequiredArgsConstructor
    @Getter
    @ConfigurationProperties("spring.thymeleaf3")
    public static class Thymeleaf3Properties {

        private final boolean decoupledLogic;

    }
}
