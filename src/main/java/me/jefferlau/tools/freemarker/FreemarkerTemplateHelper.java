package me.jefferlau.tools.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

/**
 *
 * @author lty jefferlzu@gmail.com
 * Created on 2016-08-10
 */
public class FreemarkerTemplateHelper {

    //获取要写入PDF的内容
    public static String getContent(String ftlPath, String ftlName, Object o) throws IOException, TemplateException {
        return useTemplate(ftlPath, ftlName, o);
    }

    //使用freemarker得到html内容
    public static String useTemplate(String ftlPath, String ftlName, Object o) throws IOException, TemplateException {

        String html = null;

        Template tpl = getFreemarkerConfig(ftlPath).getTemplate(ftlName);

        StringWriter writer = new StringWriter();
        tpl.process(o, writer);
        writer.flush();
        html = writer.toString();
        return html;
    }


    /**
     * 获取Freemarker配置
     *
     * @param templatePath
     * @return
     * @throws IOException
     */
    private static Configuration getFreemarkerConfig(String templatePath) throws IOException {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setDirectoryForTemplateLoading(new File(templatePath));
        config.setEncoding(Locale.CHINA, "utf-8");
        return config;
    }
}
