package com.habeat.selfhelp.selfmassage;

public class Massage {
    private String description;
    private int videoId;

    public Massage(String description, int videoId) {
        this.description = description;
        this.videoId = videoId;
    }

    public String getDescription() {
        return description;
    }

    public int getVideoId() {
        return videoId;
    }
}
