package ru.itis.springboothomework.extensions.pebble;

import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CsrfFunction implements Function {

    @Autowired
    private HttpServletRequest request;

    @Override
    public Object execute(Map<String, Object> args, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return "id=_csrf name=" + csrfToken.getParameterName() + " value=" + csrfToken.getToken();
    }

    @Override
    public List<String> getArgumentNames() {
        return Arrays.asList("csrf");
    }
}
