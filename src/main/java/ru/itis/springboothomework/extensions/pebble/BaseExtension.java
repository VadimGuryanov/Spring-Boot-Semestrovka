package ru.itis.springboothomework.extensions.pebble;

import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.spring.extension.SpringExtension;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Map;

public class BaseExtension extends SpringExtension implements BeanFactoryAware {

    /**
     * Object for a batch access to beans.
     */
    private BeanFactory beanFactory;

    @Override
    public Map<String, Function> getFunctions() {
        Map<String, Function> functions = super.getFunctions();
        functions.put("fullUri", beanFactory.getBean(FullUriFunction.class));
        functions.put("csrf", beanFactory.getBean(CsrfFunction.class));
        return functions;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

