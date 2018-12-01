package com.ctk.freemarker;


import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;


import javax.enterprise.context.ApplicationScoped;

import javax.xml.rpc.server.ServletEndpointContext;

import java.io.IOException;

@ApplicationScoped
public class TemplateAPI {

    private static final String TEMPLATE_DIRECTORY_PATH = "//WEB-INF//fm-templates";
    private static final String TEMPLATE_EXTENSION = ".ftlh";


    public Template getTemplateAPI(String templateName)
            throws IOException {

        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(TemplateAPI.class,TEMPLATE_DIRECTORY_PATH);

        return configuration.getTemplate( templateName );
    }
}
