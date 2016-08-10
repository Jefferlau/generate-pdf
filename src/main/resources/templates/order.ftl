<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${title}</title>
    <style type="text/css">
        body {
            margin: 0;
            /*font-family: Arial Unicode MS;*/
            /*font-family: "Songti SC";*/
            font-family: NSimSun;
            font-size: 12pt;
        }

        table {
            margin: auto;
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #444444;
        }

        th, td {
            border: 1px solid #444444;
            font-size: 11pt;
            /*margin-left: 5px;*/
            padding: 2pt 5pt;
        }

        thead {
            text-align: center;
        }

        div.header-left {display: none}
        div.header-right {display: none}
        div.footer-left {display: none}
        div.footer-right {display: none}
        @media print {
            div.header-left {
                display: block;
                position: running(header-left);
            }
            div.header-right {
                display: block;
                position: running(header-right);
            }
            div.footer-left {
                display: block;
                position: running(footer-left);
            }
            div.footer-right {
                display: block;
                position: running(footer-right);
            }
        }

        @page {
            margin-top:25pt;
            size: 8.5in 11in;
            @bottom-center {
                content: "page " counter(page) " of  " counter(pages);
            }
            @top-left{
                content:element(header-left);
            };
            @top-right {
                content: element(header-right)
            };
            @bottom-left {
                content: element(footer-left)
            };
            @bottom-right {
                content: element(footer-right)
            };
        }
        #pagenumber:before {
            content: counter(page);
        }
        #pagecount:before {
            content: counter(pages);
        }
        @page portrait{
            size:595.3pt 841.9pt;
            margin:36.0pt 36.0pt 36.0pt 36.0pt;
            mso-header-margin:42.55pt;
            mso-footer-margin:49.6pt;
            mso-paper-source:0;
            layout-grid:16.3pt 0pt;
            mso-layout-grid-char-alt:0;
        }
        div.portrait{
            page:portrait;
        }
        /* 分页 */
        .pageNext{page-break-after: always;}

        /* flying-saucer-flying tr 跨页问题 */
        table { page-break-inside:auto; -fs-table-paginate:paginate;border-spacing: 0;border: 1px solid white; }
        tr    { page-break-inside:avoid; page-break-after:auto;}
        thead { display:table-header-group; }
        tfoot { display:table-footer-group; }
    </style>
</head>
<body>
<div id="header">
    <!--***************页眉_start*****************-->
    <div id="header-left" class="header-left" align="left">
    </div>
    <div id="header-right" class="header-right" align="right">
        ${school}
    </div>
    <!--***************页眉_end*****************-->
</div>
<div id="footer">
    <!--***************页脚_start*****************-->
    <div id="footer-left" class="footer-left" align="left">
        <#list teachers as teacher>
            ${teacher}<#if teacher_has_next>,</#if>
        </#list>
        ${teachers?size}
    </div>
    <div id="footer-right" class="footer-right" align="right">
    </div>
    <!--***************页脚_endt*****************-->
</div>

<div>
    <table style="repeat-header:yes;repeat-footer:yes;" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">收货地址</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">学校名称</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">教师信息</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">下单人姓名</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">班级</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;">奖品总数</th>
            <th style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-top: 1px solid #000000;border-right: 1px solid #000000;">奖品明细</th>
        </tr>
        </thead>
        <#--<tfoot>
        <tr>
            <th>收货地址</th>
            <th>学校名称</th>
            <th>教师信息</th>
            <th>下单人姓名</th>
            <th>班级</th>
            <th>奖品总数</th>
            <th>奖品明细</th>
        </tr>
        </tfoot>-->
	<#list data as record>
        <tr>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.address}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.school}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.consignee}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.userName}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.theClass}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;">${record.totalCount}</td>
            <td style="border: 0px; border-bottom: 1px solid #000000; border-left: 1px solid #000000;border-right: 1px solid #000000;">${record.goodsName}</td>
        </tr>
	</#list>
    </table>
</div>
</body>
</html>
