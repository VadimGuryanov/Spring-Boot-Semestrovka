package ru.itis.springboothomework.config;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.extension.SpringExtension;
import com.mitchellbosecke.pebble.spring.servlet.PebbleViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.itis.springboothomework.extensions.pebble.BaseExtension;

import javax.servlet.ServletContext;

@Configuration
@ComponentScan("ru.itis.springboothomework.extensions.pebble")
public class PebbleViewResolverConfig {

    @Autowired
    private ServletContext servletContext;

    @Bean
    public PebbleViewResolver viewResolver() {
        PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/twig/");
        viewResolver.setSuffix(".twig");
        viewResolver.setPebbleEngine(pebbleEngine());
        return viewResolver;
    }

    @Bean
    public PebbleEngine pebbleEngine(){
        return new PebbleEngine.Builder().loader(pebbleTemplateLoader()).extension(pebbleExtension()).build();
    }

    @Bean
    public Loader<?> pebbleTemplateLoader(){
        return new ServletLoader(servletContext);
    }

    @Bean
    public SpringExtension pebbleExtension(){
        return new BaseExtension();
    }
}

