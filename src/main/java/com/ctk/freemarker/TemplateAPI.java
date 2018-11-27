package com.ctk.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TemplateAPI {
    private static final String TEMPLATE_DIRECTORY_PATH = "//WEB-INF//fm-templates";
    private static final String TEMPLATE_EXTENSION = ".ftlh";

    public Template getTemplateAPI(String templateName)
            throws IOException {

        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        Path pathLogin = Paths.get(System.getProperty("jboss.server.data.dir"));
        cfg.setDirectoryForTemplateLoading(new File(String.valueOf(pathLogin)));
        // +"/"+templateName + TEMPLATE_EXTENSION));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);

        return cfg.getTemplate(pathLogin +"\\" +templateName +TEMPLATE_EXTENSION);
    }
}
