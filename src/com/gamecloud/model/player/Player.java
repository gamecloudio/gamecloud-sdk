package com.gamecloud.model.player;

import java.io.Serializable;

public class Player implements Serializable {

    private String gameKey;
    private String id;
    private String password;

    public Player(String gameKey, String id, String password) {
        this.gameKey = gameKey;
        this.id = id;
        this.password = password;
    }

    public String getGameKey() {
        return gameKey;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
