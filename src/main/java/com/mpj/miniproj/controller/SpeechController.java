package com.mpj.miniproj.controller;
import com.mpj.miniproj.example.demo.Response;
import org.springframework.web.bind.annotation.*;
import java.io.File;

import com.mpj.miniproj.example.demo.audio.AudioRecorder;
import com.mpj.miniproj.example.demo.speech.SpeechToTextService;
import com.mpj.miniproj.example.demo.speech.FumbleDetector;
import com.mpj.miniproj.example.demo.sentiment.SentimentAnalyzer;
import com.mpj.miniproj.example.demo.database.SessionDAO;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/speech")

public class SpeechController {

    @PostMapping("/analyze")
    public Response analyze(@RequestBody Map<String, String> body) {

        String text = body.get("text");

        int fumbles = FumbleDetector.countFumbles(text);
        String sentiment = SentimentAnalyzer.analyze(text);

        SessionDAO.saveSession(text, fumbles, sentiment);

        return new Response(text, fumbles, sentiment);
    }
    @GetMapping("/test")
    public String test() {
        return "WORKING";
    }
    @GetMapping("/history")
    public List<Response> getHistory() {
        return SessionDAO.getAllSessions();
    }
    @GetMapping("/streak")
    public int getStreak() {
        return SessionDAO.getStreak();
    }



}
