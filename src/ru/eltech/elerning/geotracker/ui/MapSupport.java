package ru.eltech.elerning.geotracker.ui;

import com.teamdev.jxbrowser.Browser;
import ru.eltech.elerning.geotracker.core.model.RssRow;
import ru.eltech.elerning.geotracker.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 29.12.11
 */
public class MapSupport {
    public static final String BASE_URL = "file://" + new File("resources/index.html").getAbsolutePath();

    public static void reDrawTags(final Browser browser, final List<RssRow> rows) {
        browser.setContent("" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <title>Примеры. Простой вызов карты.</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <script src=\"http://api-maps.yandex.ru/1.1/index.xml?key=AKK3-04BAAAA5tndVwIAwAWhHXEGRNyU9lI_zU5Jf6-bzG4AAAAAAAAAAABAINiqrJ0CPCtRjYkD9E7iwUnB9Q==\" type=\"text/javascript\"></script>\n" +
                "    <script src=\"geotracker.js\" type=\"text/javascript\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        function helloKL(){\n" +
                "            alert(\"55\");\n" +
                "        }\n" +
                "        var map;\n" +
                "        YMaps.jQuery(function () {\n" +
                "            map = new YMaps.Map(YMaps.jQuery(\"#YMapsID\")[0]);\n" +
                "            map.setCenter(new YMaps.GeoPoint(37.64, 55.76), 10);\n" +
                doGenerate(rows) +
                "        });\n" +
                "\n" +
                "    </script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div id=\"YMapsID\" style=\"width:100%;height:100%\"></div>\n" +
                "</body>\n" +
                "\n" +
                "</html>" +
                "",
                BASE_URL
        );

    }

    private static String doGenerate(final List<RssRow> rows){
        final StringBuilder sb = new StringBuilder();
        createBounds(sb, rows);
        createPlacemarks(sb, rows);
        final String res = sb.toString();
        System.out.println(res);
        return res;
    }

    private static void createPlacemarks(final StringBuilder sb, final List<RssRow> rows){
        int i = 1;
        for(final RssRow row : rows){
            appendPlacemarkAddString(sb, "placemark" + i, row);
            i++;
        }
    }

    private static void appendPlacemarkAddString(final StringBuilder sb, final String varName, final RssRow row){
        sb.append("var ").append(varName).append(" = new YMaps.Placemark(new YMaps.GeoPoint(").append(escape(row.getLon())).append(",").append(escape(row.getLat())).append("));\n");
        sb.append(varName).append(".name = \"").append(escape(row.getUser())).append(" <br/> ").append(escape(row.getTitle())).append(" [").append(escape(row.getPubDate())).append("]").append("\";\n");
        sb.append(varName).append(".description = \"").append(wrapInActiveUrl(escape(row.getLink()))).append("<br/>").append(escape(row.getDescr())).append("\";\n");
        sb.append("map.addOverlay(").append(varName).append(");\n");
    }

    private static void createBounds(final StringBuilder sb, final List<RssRow> rows){
        sb.append("var points = [\n");
        for(final RssRow row : rows){
            sb.append("new YMaps.GeoPoint(").append(row.getLon()).append(",").append(row.getLat()).append("),\n");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n];\n");
        sb.append("var bounds = new YMaps.GeoCollectionBounds(points);\n");
        sb.append("map.setBounds(bounds);\n");
    }

    private static String wrapInActiveUrl(final String link){
        if(!StringUtils.isEmpty(link)){
            if(link.startsWith("http")){
                return "<a target='_blank' href='" + link + "'>" + link + "</a>";
            }
        } else {
            return "";
        }
        return link;
    }

    private static String escape(final String in){
        return StringUtils.emptyIfNull(in).replaceAll("['\"]", "");
    }

}
