package com.thunderexchange.appservice.util;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.List;

public class JsonUtil {

    private static Gson gson = new Gson();

    private static JsonParser jsonParser = new JsonParser();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }


    public static <T> List<T> fromJsonArr(String json, Class<T> classOfListT) throws JsonSyntaxException {
        List<T> beans = Lists.newArrayList();
        for (JsonElement jsonBean : jsonParser.parse(json).getAsJsonArray()) {
            T bean = gson.fromJson(jsonBean, classOfListT);//解析
            beans.add(bean);
        }
        return beans;
    }


}
