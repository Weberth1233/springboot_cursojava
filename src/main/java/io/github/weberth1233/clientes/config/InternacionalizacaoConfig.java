package io.github.weberth1233.clientes.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    //Configurando mensagens de validação
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //Utilizando arquivo da raiz do projeto chamado messages
        messageSource.setBasename("classpath:messages");
        //Configurando correções ortográficas
        messageSource.setDefaultEncoding("ISO-8859-1");
        //Messagem de acordo com a localização da maquina do individuo
        messageSource.setDefaultLocale( Locale.getDefault() );
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
