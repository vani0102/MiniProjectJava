package com.mpj.miniproj.example.demo;

public class Response {
    public String text;
    public int fumbles;
    public String sentiment;

    public Response(String text, int fumbles, String sentiment) {
        this.text = text;
        this.fumbles = fumbles;
        this.sentiment = sentiment;
    }
}
