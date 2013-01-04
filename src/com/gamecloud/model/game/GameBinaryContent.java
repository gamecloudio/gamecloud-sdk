package com.gamecloud.model.game;

import java.util.Arrays;

public class GameBinaryContent {

    private String name;
    private String digest;
    private String contentType;
    private byte[] content;

    public GameBinaryContent(String name, String digest, String contentType, byte[] content) {
        this.name = name;
        this.digest = digest;
        this.contentType = contentType;
        this.content = Arrays.copyOf(content, content.length);
    }

    public String getName() {
        return this.name;
    }

    public String getDigest() {
        return this.digest;
    }

    public String getContentType() {
        return this.contentType;
    }

    public byte[] getContent() {
        return this.content;
    }
}