package com.zfl19.kernel.utils;

import com.zfl19.kernel.constants.WallhavenApi;
import com.zfl19.kernel.constants.WallhavenLoaclSite;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author: 19zfl
 * @description: 下载每个图片的工具
 * @date 2023-05-27
 */
public class DownLoadUtil {

    public static String downLoad(String picUrl) {

        String consoleInfo = "";
        try {
            // 获取URL对象
            URL url = new URL(picUrl);
            // 获取网络连接对象
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            // 存入cookie
            urlConnection.setRequestProperty(WallhavenApi.API_COOKIE, WallhavenApi.API_COOKIE_VALUE);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            UUID uuid = UUID.randomUUID();
            String uuidName = uuid.toString();
            long nowTimes = System.currentTimeMillis();
            String fileName = uuidName + nowTimes;
            if (picUrl.endsWith(".png")) {
                OutputStream outputStream = new FileOutputStream(WallhavenLoaclSite.localSite + fileName + ".png");
                IOUtils.copy(inputStream, outputStream);
                consoleInfo = fileName + ".png写入完毕";
            } else {
                OutputStream outputStream = new FileOutputStream(WallhavenLoaclSite.localSite + fileName + ".jpg");
                IOUtils.copy(inputStream, outputStream);
                consoleInfo =  fileName + ".jpg写入完毕";
            }
        } catch (Exception e) {
            throw new RuntimeException("IO超时...");
        }
        return consoleInfo;
    }

}
