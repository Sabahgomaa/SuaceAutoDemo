package com.swaglabs.utils;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.internal.shadowed.jackson.core.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonReader;
    String jsonFileName;

    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject date = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = date.toJSONString();
        } catch (Exception e) {
            LogsUtil.error(e.getMessage());
        }
    }

    public String getJsonData(String jsonPath) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage(), "No Result for Json Path :" + jsonPath + "in the json file: ");
        }
        LogsUtil.info("Json Path :"+jsonPath+"in this json file"+this.jsonFileName+"has value");
        return testData;
    }
}
