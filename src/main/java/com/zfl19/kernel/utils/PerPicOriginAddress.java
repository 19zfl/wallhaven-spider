package com.zfl19.kernel.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 19zfl
 * @description: 获取每个a标签中url的详细源地址
 * @date 2023-05-27
 */
public class PerPicOriginAddress {

    public static List<String> getOriginUrlPerPic(String url) {
        // 存放每个图片的源地址提供给IO操作
        List<String> originUrlList = new ArrayList<>();
        try {
            // 获取a标签图片地址网页的文档对象
            Document document = Jsoup.connect(url).get();
            // 获取img标签元素对象
            Element wallpaperElement = document.getElementById("wallpaper");
            // 获取img标签内src的值
            String picUrl = wallpaperElement.attr("src");
            // 存入集合中
            originUrlList.add(picUrl);
        } catch (IOException e) {
            throw new RuntimeException("请求图片源地址超时...");
        }
        return originUrlList;
    }

}
