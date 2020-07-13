package com.example.speechrecognition;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.RecognizerResultsIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {
    private static  final int RECOGNIZER_RESULT = 1;
    Button speakBtn;
    TextView spokenText , replyText, noteText;
    TextToSpeech txtToSpeech;
    ArrayList<String> resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakBtn = findViewById(R.id.speakBtn);
        spokenText = findViewById(R.id.spokenText);
        replyText  = findViewById(R.id.replyText);
        noteText = findViewById(R.id.noteText);

        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "say your name");
                startActivityForResult(speechIntent, RECOGNIZER_RESULT);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(this, "yesss", Toast.LENGTH_SHORT);

        if (requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK) {
            resultText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String s = "Your name is: " + resultText.get(0);
            spokenText.setText(s);


        txtToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int lang = txtToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        noteText.setText ("*Click on robot to hear reply");
        replyText.setText("\uD83E\uDD16");

        replyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //read whats in replyText
                String s = "Hello " + resultText.get(0)  + " !";
                int read = txtToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

    }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
