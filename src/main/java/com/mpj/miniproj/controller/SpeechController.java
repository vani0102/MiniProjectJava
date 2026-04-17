package com.mpj.miniproj.controller;
import com.mpj.miniproj.example.demo.Response;
import org.springframework.web.bind.annotation.*;
import java.io.File;

import com.mpj.miniproj.example.demo.audio.AudioRecorder;
import com.mpj.miniproj.example.demo.speech.SpeechToTextService;
import com.mpj.miniproj.example.demo.speech.FumbleDetector;
import com.mpj.miniproj.example.demo.sentiment.SentimentAnalyzer;
import com.mpj.miniproj.example.demo.database.SessionDAO;

@RestController
@CrossOrigin
@RequestMapping("/api/speech")

public class SpeechController {
    @PostMapping("/analyze")
    public Response analyze() {

        try {
            File audioFile = AudioRecorder.recordAudio(5);

            String text = SpeechToTextService.convertSpeechToText(audioFile.getPath());

            int fumbles = FumbleDetector.countFumbles(text);
            String sentiment = SentimentAnalyzer.analyze(text);

            SessionDAO.saveSession(text, fumbles, sentiment);

            return new Response(text, fumbles, sentiment);

        } catch (Exception e) {
            e.printStackTrace();
            return new Response("Error", 0, "Error");
        }
    }@GetMapping("/test")
    public String test() {
        return "WORKING";
    }

}
