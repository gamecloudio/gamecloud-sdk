package com.gamecloud.model.game;

import org.json.JSONException;
import org.json.JSONObject;

public class GameSaveInfo {

    private String saveKey;
    private String saveDate;
    private String saveName;

    public GameSaveInfo(String saveKey, String saveDate, String saveName) {
        this.saveKey = saveKey;
        this.saveDate = saveDate;
        this.saveName = saveName;
    }

    public GameSaveInfo(JSONObject object) throws JSONException {
        this(object.getString("save_key"), object.getString("date"), object.getString("save_name"));
    }

    public String getSaveKey() {
        return this.saveKey;
    }

    public String getSaveDate() {
        return this.saveDate;
    }

    public String getSaveName() {
        return this.saveName;
    }
}
