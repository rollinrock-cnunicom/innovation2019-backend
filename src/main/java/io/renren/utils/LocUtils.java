package io.renren.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class LocUtils {

    // 参考 https://lbs.amap.com/api/webservice/guide/api/georegeo
    private static final String uriPrefix = "https://restapi.amap.com/v3/geocode/regeo?key=c0a1769ceaa72ec5fe5437d116caef4f";


    public static String locateCounty(double longitude, double latitude) {
        if (longitude <= 0 || latitude <= 0) return "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uriPrefix.concat(String.format("&location=%.6f,%.6f", longitude, latitude)))
                .build();
        log.info("地区信息获取url参数：{}", request.url().toString());
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String data = response.body().string();
//                log.info("获取经纬度[{}，{}]对应的地区信息返回值：{}", longitude, latitude, data);
                JSONObject jsonObject = JSONObject.parseObject(data);
                if (!jsonObject.getString("status").equalsIgnoreCase("1"))
                    return "";
                return jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent").getString("district");
            } else
                return "";
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("根据经纬度获取地区信息失败");
        }
    }

    public static boolean locatedInLanxiOrPujiang(double longitude, double latitude){
        String district = locateCounty(longitude, latitude);
        return district.startsWith("兰溪") || district.startsWith("浦江");
    }

    public static void main(String[] args) {
//        System.out.println(String.format("&location=%.6f,%.6f", 119.663884, 29.071339));
        System.out.println(LocUtils.locateCounty(120.075058,29.306841));
    }
}
