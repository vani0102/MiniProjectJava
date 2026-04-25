package com.mpj.miniproj.example.demo.speech;

import java.util.Set;

public class FumbleDetector {

    private static final String[] fillers = {
            "um", "uh", "like", "you know", "so", "actually", "basically"
    };

    public static int countFumbles(String text) {
        if (text == null) return 0;

        int count = 0;
        String lower = text.toLowerCase();

        for (String filler : fillers) {
            count += lower.split("\\b" + filler + "\\b").length - 1;
        }

        return count;
    }
}