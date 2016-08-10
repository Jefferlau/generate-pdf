package me.jefferlau;


import me.jefferlau.tools.flying.PdfHelper;
import me.jefferlau.tools.flying.PdfUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static me.jefferlau.tools.itext.PdfHelper.generateToFile;

/**
 *
 * @author lty jefferlzu@gmail.com
 * Created on 2016-08-10
 */
public class App {
    public static void main(String[] args) throws Exception {

        try {
            Map<Object, Object> data = new HashMap<>();
            data.put("title", "发货单");
            data.put("school", "安定镇西卢小学.EMS");

            java.util.List<Map<Object, Object>> records = new ArrayList<>();
            Map<Object, Object> record = null;

            Set<String> teachers = new HashSet<>();

            for (int i = 0; i < 20; i++) {
                record = new HashMap<>();
                record.put("address", "北京市辖区大兴区安定镇西芦小学");
                record.put("school", "安定镇西卢小学");
                record.put("consignee", "姜帆<br/><b>17012345678</b><br/>英语");
                record.put("userName", "陈佳依");
                record.put("theClass", "六年级1班");
                record.put("totalCount", "奖品总数:1");
                record.put("goodsName", "圆印章_X1<br/>遥控直升机_X1");

                records.add(record);

                teachers.add("姜帆");
            }

            data.put("data", records);
            data.put("teachers", teachers);

            String path = PdfHelper.getPath();

            System.out.println(path);
            System.out.println(System.getProperty("user.dir"));

            // flying-saucer
            PdfUtils.generateToFile(path + "templates/", "order.ftl", path + "/", data, System.getProperty("user.dir") + "/flying-saucer.pdf");

            // TODO 未解决页眉页脚页码问题
            // iText
            generateToFile(path + "templates/", "order.ftl", path + "/", data, System.getProperty("user.dir") + "/itext.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        //--------------------------------------------
    }

}
