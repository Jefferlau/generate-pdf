package me.jefferlau.tools.itext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import me.jefferlau.tools.freemarker.FreemarkerTemplateHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *
 * @author lty jefferlzu@gmail.com
 * Created on 2016-08-10
 */
public class PdfHelper {

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getPath() {
        return me.jefferlau.tools.flying.PdfHelper.class.getResource("/").getPath();
    }

    /**
     * 生成PDF到文件
     * @param ftlPath       模板文件路径（不含文件名）
     * @param ftlName       模板文件吗（不含路径）
     * @param imageDiskPath 图片的磁盘路径
     * @param data          数据
     * @param outputFile    目标文件（全路径名称）
     * @throws Exception
     */
    public static void generateToFile(String ftlPath, String ftlName, String imageDiskPath, Object data, String outputFile) throws Exception {
        String html = FreemarkerTemplateHelper.getContent(ftlPath, ftlName, data);
        String path = getPath();

        Document doc = new Document(PageSize.A4);
        //如果是web做下载，后面的传入response的输出流，同时需要设置response的ContentType以及指定Content-Disposition为attachment同时设定fileName，fileName记得URLEncoder
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outputFile));
        //设置页面边距
        doc.setMargins(20, 20, 20, 20);
        doc.open();
        generateToFile(html, path, doc, writer);
        doc.close();
    }

    public static void generateToServletOutputStream(String ftlPath, String ftlName, String imageDiskPath, Object data, String outputFile, HttpServletResponse response) throws Exception {
        String html = FreemarkerTemplateHelper.getContent(ftlPath, ftlName, data);
        String path = getPath();

        Document doc = new Document(PageSize.A4);
        //如果是web做下载，后面的传入response的输出流，同时需要设置response的ContentType以及指定Content-Disposition为attachment同时设定fileName，fileName记得URLEncoder
        response.setContentType("application/flying");
        response.setHeader("Content-Disposition", "attachment;fileName=" + (outputFile.contains("/")?outputFile.substring(outputFile.lastIndexOf("/"), outputFile.length() - 1):outputFile));
        PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());
        //设置页面边距
        doc.setMargins(20, 20, 20, 20);
        doc.open();
        generateToFile(html, path, doc, writer);
        doc.close();
    }

    private static void generateToFile(String html, String path, Document doc, PdfWriter writer) throws DocumentException, IOException {
        //这里可以代码创建pdf的部分页面（如更加精细的封面等）
        //......
        //设置HTML转PDF时页面边距（建议放在open方法前面，如果不放在open方法前面则必须后面加上newPage才能生效）
        // doc.setMargins(20, 20, 20, 20);
        // doc.newPage();

        BaseFont baseCnFont = BaseFont.createFont(path + "fonts/Songti.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        BaseFont cnF1 = BaseFont.createFont(path + "fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        BaseFont cnF2 = BaseFont.createFont(path + "fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        XMLWorkerHelper.getInstance().parseXHtml(writer, doc, new ByteArrayInputStream(html.getBytes()), Charset.forName("UTF-8"), new FontProvider() {
            @Override
            public boolean isRegistered(String s) {
                return false;
            }

            public Font getFont(String fontFamily, String charset, boolean arg2, float size, int style, BaseColor color) {
                //根据CSS中不同的字体名返回不同的字体实例
                if("cssFontName1".equalsIgnoreCase(fontFamily)){
                    return new Font(cnF1,size,style,color);
                }else if("cssFontName2".equalsIgnoreCase(fontFamily)){
                    return new Font(cnF2,size,style,color);
                }else{
                    //默认字体
                    return new Font(baseCnFont,size,style,color);
                }
            }
        });
    }
}
