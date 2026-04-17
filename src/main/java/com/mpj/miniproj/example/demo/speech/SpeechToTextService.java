package com.mpj.miniproj.example.demo.speech;

import org.vosk.Model;
import org.vosk.Recognizer;

import java.io.FileInputStream;

public class SpeechToTextService {

    public static String convertSpeechToText(String audioFilePath) {
        try {
            Model model = new Model("C:\\vosk-model"); // change path if needed
            Recognizer recognizer = new Recognizer(model, 16000);

            FileInputStream ais = new FileInputStream(audioFilePath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = ais.read(buffer)) >= 0) {
                recognizer.acceptWaveForm(buffer, bytesRead);
            }

            String result = recognizer.getFinalResult();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}