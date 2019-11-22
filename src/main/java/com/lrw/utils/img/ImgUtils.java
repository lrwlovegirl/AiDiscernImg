package com.lrw.utils.img;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lrw.utils.HttpClientUtils;
import com.lrw.utils.HttpRequestData;

public class ImgUtils {
	/**
     * 获取识别验证码
     * @param imageUrl
     * @return
     */
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	
	private static String ACCESS_TOKEN="";
	private static String OCRUrl="";
	
    public static String OCRVCode(String imageUrl){
        String VCode = "";

        if (ACCESS_TOKEN==null) {
            return VCode;
        }
        OCRUrl = OCRUrl + "?access_token=" + ACCESS_TOKEN;

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        HashMap<String, String> params = new HashMap<>();
        imageUrl = ImageBase64ToStringUtils.imageToStringByBase64(imageUrl);
        params.put("image", imageUrl);

        HttpRequestData httpRequestData = new HttpRequestData();
        httpRequestData.setHeaders(headers);
        httpRequestData.setRequestMethod("post");
        httpRequestData.setParams(params);
        httpRequestData.setRequestUrl(OCRUrl);
        HttpResponse response = HttpClientUtils.execute(httpRequestData);
        String json = "";
        if (response.getStatusLine().getStatusCode() == 200) {
            try {
                json = IOUtils.toString(response.getEntity().getContent());
                JSONObject jsonObject = JSONObject.parseObject(json);
                JSONArray wordsResult = jsonObject.getJSONArray("words_result");
                VCode = wordsResult.getJSONObject(0).getString("words");
            } catch (IOException e) {
                logger.error("请求识别失败！", e);
            }
        }
        return VCode;
    }
    
    /**
     * 将本地图片进行Base64位编码
     * @param imageFile
     * @return
     */
    public static String encodeImgageToBase64(String imageFile) {
        // 其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        return Base64Util.encode(data);
    }
}
