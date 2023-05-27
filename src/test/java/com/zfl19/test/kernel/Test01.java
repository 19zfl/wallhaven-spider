package com.zfl19.test.kernel;

import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 19zfl
 * @description:
 * @date 2023-05-27
 */
public class Test01 {

    public static void main(String[] args) throws Exception {

        Test01 v2 = new Test01();
        // 自定义开始页数
        int id = 1;
        // 自定义类型, 类型查看官网
        String type = "random";
        Document document = null;
        for (int page = 6; ; page++) {
            String website = "https://wallhaven.cc/"+type+"?seed=BMpMMm&page=" + page;
            try {
                document = Jsoup.parse(new URL(website), 10000);
                Element element = document.getElementById("thumbs");
                Elements previewUrl = element.getElementsByClass("preview");
                for (Element preUrl : previewUrl) {
                    String href = preUrl.attr("href");
                    v2.getImgUrl(href, id);
                    id++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void getImgUrl(String url, int id) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        map.put("accept-language", "zh-CN,zh;q=0.9");
        map.put("cache-control", "max-age=0");
        map.put("sec-ch-ua", "\"Not?A_Brand\";v=\"8\", \"Chromium\";v=\"108\", \"Google Chrome\";v=\"108\"");
        map.put("sec-ch-ua-mobile", "?0");
        map.put("sec-ch-ua-platform", "\"Windows\"");
        map.put("sec-fetch-dest", "document");
        map.put("sec-fetch-mode", "navigate");
        map.put("sec-fetch-site", "none");
        map.put("sec-fetch-user", "?1");
        map.put("upgrade-insecure-requests", "1");
        map.put("Cookie", "cf_clearance=y_eusLB64NRZJCi4F9myYON_57FjutcdUoOiIgs6Srg-1685096636-0-250");

        try {
            Connection.Response response = Jsoup.connect(url)
                    .headers(map)
                    .timeout(10000)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();
            String body = response.body();
            Document document = Jsoup.parse(body);
            Element showcase = document.getElementById("showcase");
            Elements img = showcase.getElementsByTag("img");
            for (Element element : img) {
                String src = element.attr("src");
                URL inurl = new URL(src);
                HttpURLConnection conn = (HttpURLConnection) inurl.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Cookie", "cf_clearance=y_eusLB64NRZJCi4F9myYON_57FjutcdUoOiIgs6Srg-1685096636-0-250");
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                if (src.endsWith(".png")) {
                    OutputStream outputStream = new FileOutputStream("D:\\plugins\\spider\\wallhaven\\demo\\" + id + ".png");
                    IOUtils.copy(inputStream, outputStream);
                    System.out.println(id + ".png写入完毕");
                } else {
                    OutputStream outputStream = new FileOutputStream("D:\\plugins\\spider\\wallhaven\\demo\\" + id + ".jpg");
                    IOUtils.copy(inputStream, outputStream);
                    System.out.println(id + ".jpg写入完毕");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
