package com.mpj.miniproj.example.demo.speech;

import org.vosk.Model;
import org.vosk.Recognizer;

import java.io.FileInputStream;
import java.io.InputStream;

public class SpeechToTextService {

    public static String convertSpeechToText(String path) {
        try {
            Model model = new Model("model");

            InputStream ais = new FileInputStream(path);

            Recognizer recognizer = new Recognizer(model, 16000);

            byte[] buffer = new byte[4096];
            int bytesRead;

            String result = "";

            while ((bytesRead = ais.read(buffer)) >= 0) {
                if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                    result = recognizer.getResult();
                }
            }

            result += recognizer.getFinalResult();

            ais.close();
            recognizer.close();
            model.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}