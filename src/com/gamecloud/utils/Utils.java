package com.gamecloud.utils;

import com.gamecloud.exception.GameCloudException;
import com.gamecloud.http.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    public static final String ADDRESS = "http://54.245.119.57:8080";

    public static void check(Response res) throws JSONException, GameCloudException {
        if (res.getStatusCode() != 200) {
            String msg = new JSONObject(res.getMessage()).getString(ERROR);
            throw new GameCloudException(msg);
        }
    }

    public static String checkReturnString(Response res) throws JSONException, GameCloudException {
        if (res.getStatusCode() != 200) {
            String msg = new JSONObject(res.getMessage()).getString(ERROR);
            throw new GameCloudException(msg);
        } else {
            return new JSONObject(res.getMessage()).getString(OK);
        }
    }

    public static JSONObject checkReturnJSONObject(Response res) throws JSONException, GameCloudException {
        if (res.getStatusCode() != 200) {
            String msg = new JSONObject(res.getMessage()).getString(ERROR);
            throw new GameCloudException(msg);
        } else {
            return new JSONObject(res.getMessage()).getJSONObject(OK);
        }
    }

    public static JSONArray checkReturnJSONArray(Response res) throws JSONException, GameCloudException {
        if (res.getStatusCode() != 200) {
            String msg = new JSONObject(res.getMessage()).getString(ERROR);
            throw new GameCloudException(msg);
        } else {
            return new JSONObject(res.getMessage()).getJSONArray(OK);
        }
    }
}