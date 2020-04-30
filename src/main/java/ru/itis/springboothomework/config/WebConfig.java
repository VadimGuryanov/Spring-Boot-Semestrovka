package ru.itis.springboothomework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.itis.springboothomework.conventers.JsonConverter;
import ru.itis.springboothomework.models.CovidData;


@Configuration
@ComponentScan(basePackages = {"ru.itis.springboothomework.controllers"})
@EnableWebMvc
@Import({PebbleViewResolverConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/assets/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/assets/css/");
    registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
    registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/assets/static/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/error").setViewName("error/error");
  }

  @Override
  public void addFormatters(FormatterRegistry formatterRegistry) {
    formatterRegistry.addConverter(summaryJsonConverter());
  }

  @Bean
  public JsonConverter summaryJsonConverter(){
    return new JsonConverter(CovidData.class);
  }


}

/*
//  @Bean
//  public MessageSource messageSource() {
//    ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
//    res.setBasenames("classpath:i18n/messages", "classpath:i18n/validation");
//    res.setCacheSeconds(0);
//    res.setDefaultEncoding("UTF-8");
//    res.setUseCodeAsDefaultMessage(false);
//    return res;
//  }
//
//  @Bean
//  public LocaleResolver localeResolver() {
//    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//    localeResolver.setCookieName("lang");
//    localeResolver.setDefaultLocale(new Locale("ru", "RU"));
//    return localeResolver;
//  }



//  public SimpleJtwigFunction getMyFunction() {
//    return new SimpleJtwigFunction() {
//      @Override
//      public String name() {
//        return "mvcUriBuilder";
//      }
//
//      @Override
//      public Object execute(FunctionRequest functionRequest) {
//        String controllerFun = (String) functionRequest.getArguments().get(0);
//        return MvcUriComponentsBuilder.fromMappingName(controllerFun).build();
//      }
//    };
//  }
//
//  public SimpleJtwigFunction getDateFunction() {
//    return new SimpleJtwigFunction() {
//      @Override
//      public String name() {
//        return "getDate";
//      }
//
//      @Override
//      public Object execute(FunctionRequest functionRequest) {
//        Date dateFun = (Date) functionRequest.getArguments().get(0);
//        Date dateNow = new Date();
//        if (dateNow.getDay() == dateFun.getDay() && dateFun.getYear() == dateNow.getYear()) {
//
//          if (Math.abs(dateFun.getHours() - dateNow.getHours()) >= 1 && dateNow.getMinutes() - dateFun.getMinutes() <=0) {
//            return "1 hour ago";
//          }
//
//          if (Math.abs(dateFun.getMinutes() - dateNow.getMinutes()) > 10) return "10 minute ago";
//
//        }
//        return dateFun.toString();
//      }
//    };
//  }
//
//  public EnvironmentConfiguration getConfiguration() {
//    return EnvironmentConfigurationBuilder
//            .configuration()
//            .functions()
//            .add(getMyFunction())
//            .add(getDateFunction())
//            .and()
//            .build();
//  }

//  @Bean
//  public ViewResolver viewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setViewClass(JstlView.class);
//    resolver.setPrefix("/WEB-INF/views/");
////    JtwigViewResolver resolver = new JtwigVie wResolver();
////    JtwigRenderer renderer = new JtwigRenderer(getConfiguration());
////    resolver.setPrefix("web:/WEB-INF/views/");
////    resolver.setSuffix(".html.twig");
//    resolver.setSuffix(".jsp");
////    resolver.setRenderer(renderer);
//    resolver.setRedirectContextRelative(false);
//    return resolver;
//  }
 */
