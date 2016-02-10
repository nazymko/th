package org.nazymko.controller.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nazymko.controller.task.THParser;
import org.nazymko.thehomeland.parser.THLParser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

public class TemplateInterceptor implements HandlerInterceptor {
    @Resource
    private InternalResourceViewResolver resolver;
    @Resource
    private String prefix, suffix, siteName, jsInclude, cssInclude;
    @Resource
    THLParser parser;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            //skip  resource requests
            return;
        }
        Map<String, Object> model = modelAndView.getModel();

        String view = getViewName(modelAndView);
        //making insertion inside of jsp
        model.put("TEMPLATE_BODY", prefix + view + suffix);

        modelAndView.setViewName("template/default");


        setTitle(model);
        model.put("js", jsInclude);
        model.put("css", cssInclude);
        model.put("status", parser.isActive());

    }

    private void setTitle(Map<String, Object> model) {
        if (!model.containsKey("title")) {
            model.put("title", siteName);
        }
    }

    private String getViewName(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        if (viewName.startsWith("redirect:")) {
            viewName = viewName.replace("redirect:", "");
        }
        return viewName;
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
