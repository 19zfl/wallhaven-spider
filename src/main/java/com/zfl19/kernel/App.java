package com.zfl19.kernel;


import com.zfl19.kernel.constants.ParameterField;
import com.zfl19.kernel.constants.WallhavenType;
import com.zfl19.kernel.constants.WallhavenWebsite;
import com.zfl19.kernel.utils.DownLoadUtil;
import com.zfl19.kernel.utils.PerPicDetailAddress;
import com.zfl19.kernel.utils.PerPicOriginAddress;

import java.util.List;

/**
 * @author: 19zfl
 * @description: 启动类
 * @date 2023-05-26
 */
public class App {

    public static void main(String[] args) {

        String website = WallhavenWebsite.WALLHAVEN_WEBSITE + WallhavenType.TYPE_LATEST + ParameterField.FIELD_PAGE + ParameterField.PAGE_NUM;
        List<String> targetUrl = PerPicDetailAddress.getTargetUrl(website);
        for (String s : targetUrl) {
            List<String> originUrlPerPic = PerPicOriginAddress.getOriginUrlPerPic(s);
            for (String s1 : originUrlPerPic) {
                String s2 = DownLoadUtil.downLoad(s1);
                System.out.println(s2);
            }
        }

    }

}
