package com.autogram.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class FreeMarker {
    public static void setTemplate(ServletContext servletContext, HashMap<String, Object> data,
                                   String view, HttpServletResponse response) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);

        configuration.setServletContextForTemplateLoading(servletContext, "WEB-INF/view/");

        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        Template template = null;
        try {
            template = configuration.getTemplate(view);
            template.process(data, response.getWriter());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
