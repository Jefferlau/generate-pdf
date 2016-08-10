package me.jefferlau.tools.flying;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 *
 * @author lty jefferlzu@gmail.com
 * Created on 2016-08-10
 */
public class PdfHelper {

    public static ITextRenderer getRender() throws DocumentException, IOException {

        ITextRenderer render = new ITextRenderer();

        String path = getPath();
        //添加字体，以支持中文
        render.getFontResolver().addFont(path + "fonts/Songti.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(path + "fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.getFontResolver().addFont(path + "fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        return render;
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

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getPath() {
        return PdfHelper.class.getResource("/").getPath();
    }

}
