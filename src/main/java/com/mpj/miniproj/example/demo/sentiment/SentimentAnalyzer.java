package com.mpj.miniproj.example.demo.sentiment;

import java.util.Set;

public class SentimentAnalyzer {

    private static final String[] positiveWords = {
            "good", "great", "awesome", "happy", "excellent", "love"
    };

    private static final String[] negativeWords = {
            "bad", "sad", "terrible", "hate", "worst", "angry"
    };

    public static String analyze(String text) {
        if (text == null || text.isEmpty()) return "neutral";

        int score = 0;
        String lower = text.toLowerCase();

        for (String word : positiveWords) {
            if (lower.contains(word)) score++;
        }

        for (String word : negativeWords) {
            if (lower.contains(word)) score--;
        }

        if (score > 0) return "positive";
        if (score < 0) return "negative";
        return "neutral";
    }
}