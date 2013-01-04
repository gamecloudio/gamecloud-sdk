package com.gamecloud.model.game;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameSave extends GameSaveInfo {

    private List<GameBinaryContent> binaryAttachments = new ArrayList<GameBinaryContent>();

    @SuppressWarnings("unchecked")
    public GameSave(JSONObject object) throws JSONException {
        super(object);
        JSONObject attachments = object.getJSONObject("attachments");
        Iterator<String> iterator = attachments.keys();
        while (iterator.hasNext()) {
            String name = iterator.next();
            JSONObject save = attachments.getJSONObject(name);
            byte[] content = Base64.decode(save.getString("content"), Base64.DEFAULT);
            String contentType = save.getString("content_type");
            //String digest = save.getString("digest");
            binaryAttachments.add(new GameBinaryContent(name, null, contentType, content));
        }
    }

    public List<GameBinaryContent> getBinaryAttachments() {
        return this.binaryAttachments;
    }
}