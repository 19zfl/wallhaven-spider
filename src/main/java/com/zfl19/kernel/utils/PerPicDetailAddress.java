package com.zfl19.kernel.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 19zfl
 * @description: 获取每页图片详细路径地址集合
 * @date 2023-05-26
 */
public class PerPicDetailAddress {

    public static List<String> getTargetUrl(String url) {

        // 存放每页图片的a标签内图片详细地址
        List<String> urlList = new ArrayList<>();
        try {
            // 获取该页文档对象
            Document document = Jsoup.connect(url).get();
            // 获取每个图片a标签父标签
            Element thumbsElement = document.getElementById("thumbs");
            // 获取每个a标签
            Elements previewElements = thumbsElement.getElementsByClass("preview");
            for (Element previewElement : previewElements) {
                // 得到每个a标签内图片地址
                String href = previewElement.attr("href");
                // 存入集合urlList中
                urlList.add(href);
            }
        } catch (IOException e) {
            throw new RuntimeException("请求图片详细地址超时...");
        }
        return urlList;
    }

}
