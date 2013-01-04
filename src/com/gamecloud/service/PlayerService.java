package com.gamecloud.service;

import com.gamecloud.exception.GameCloudException;
import com.gamecloud.http.property.ByteArrayProperty;
import com.gamecloud.http.property.Property;
import com.gamecloud.http.request.GetRequest;
import com.gamecloud.http.request.MultiPartPostRequest;
import com.gamecloud.http.request.PostRequest;
import com.gamecloud.http.request.RequestException;
import com.gamecloud.http.response.Response;
import com.gamecloud.model.developer.Developer;
import com.gamecloud.model.game.GameSave;
import com.gamecloud.model.game.GameSaveInfo;
import com.gamecloud.model.player.Player;
import com.gamecloud.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class PlayerService {

    public static void saveToCloud(String devId, String devPass, Player player) throws GameCloudException {
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("developer_id", devId),
                new Property("dev_password", devPass),
                new Property("player_id", player.getId()),
                new Property("password", player.getPassword()),
                new Property("game_key", player.getGameKey())
        });
        try {
            PostRequest request = new PostRequest();
            URL url = new URL(Utils.ADDRESS + "/register_player");
            Utils.check(request.request(url, properties));
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }

    public static void saveToCloud(Developer dev, Player player) throws GameCloudException {
        saveToCloud(dev.getId(), dev.getPassword(), player);
    }

    public static String createSave(Player player, String name, List<ByteArrayProperty> contents) throws GameCloudException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("game_key", player.getGameKey()),
                new Property("player_id", player.getId()),
                new Property("password", player.getPassword()),
                new Property("save_name", name),
                new Property("date", df.format(Calendar.getInstance().getTime()))
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/create_save");
            MultiPartPostRequest request = new MultiPartPostRequest();
            return Utils.checkReturnString(request.request(url, properties, contents));
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }

    public static JSONObject readSave(Player player, String saveKey) throws GameCloudException {
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("game_key", player.getGameKey()),
                new Property("player_id", player.getId()),
                new Property("password", player.getPassword()),
                new Property("save_key", saveKey)
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/read_save");
            GetRequest request = new GetRequest();
            Response res = request.request(url, properties);
            return Utils.checkReturnJSONObject(res);
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        }
    }

    public static GameSave readSave1(Player player, GameSaveInfo info) throws GameCloudException {
        JSONObject save = readSave(player, info.getSaveKey());
        try {
            return new GameSave(save);
        } catch (JSONException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        }
    }

    public static JSONArray listSaves1(Player player) throws GameCloudException {
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("game_key", player.getGameKey()),
                new Property("player_id", player.getId()),
                new Property("password", player.getPassword())
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/list_game_saves");
            GetRequest request = new GetRequest();
            Response res = request.request(url, properties);
            return Utils.checkReturnJSONArray(res);
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }

    public static ArrayList<GameSaveInfo> listSaves2(Player player) throws GameCloudException {
        try {
            ArrayList<GameSaveInfo> saveInfo = new ArrayList<GameSaveInfo>();
            JSONArray saves = listSaves1(player);
            for (int i = 0; i < saves.length(); i++) {
                saveInfo.add(new GameSaveInfo(saves.getJSONObject(i)));
            }
            return saveInfo;
        } catch (JSONException e) {
            throw new GameCloudException("Failed to build save info: " + e.getMessage());
        }
    }

    public static boolean authorize(Player player) throws GameCloudException {
        try {
            listSaves1(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
