package com.zfl19.test.kernel;

import com.zfl19.kernel.constants.ParameterField;
import com.zfl19.kernel.constants.WallhavenType;
import com.zfl19.kernel.constants.WallhavenWebsite;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 19zfl
 * @description:
 * @date 2023-05-26
 */
public class JsoupApiTest {

    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();
        String website = WallhavenWebsite.WALLHAVEN_WEBSITE + WallhavenType.TYPE_LATEST + ParameterField.FIELD_PAGE + ParameterField.PAGE_NUM;
        Document document = Jsoup.connect(website).get();
        Element thumbs = document.getElementById("thumbs");
        // 图片a标签集合
        Elements preview = thumbs.getElementsByClass("preview");
        for (Element element : preview) {
            String hrefUrl = element.attr("href");
            list.add(hrefUrl);
        }
        for (String url : list) {
            Document document1 = Jsoup.connect(url).get();
            System.out.println(document1);
        }
//        Connection.Response execute = Jsoup.connect("https://wallhaven.cc/w/zyodvj").timeout(20000).ignoreContentType(true).ignoreHttpErrors(true).execute();
//        String body = execute.body();
//        Document document1 = Jsoup.parse(body);
//        Element showcase = document1.getElementById("showcase");
//        Element wallpaper = showcase.getElementById("wallpaper");
//        String src = wallpaper.attr("src");
//        System.out.println(src);
//        URL url = new URL(src);
//        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//        urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        urlConnection.setDoInput(true);
//        urlConnection.setRequestMethod("GET");
//        urlConnection.connect();
//        InputStream inputStream = urlConnection.getInputStream();
//        OutputStream outputStream = new FileOutputStream("D:\\plugins\\spider\\wallhaven\\demo\\" + "demo" + ".jpg");
//        IOUtils.copy(inputStream, outputStream);
//        System.out.println("写入完毕");

    }

}
