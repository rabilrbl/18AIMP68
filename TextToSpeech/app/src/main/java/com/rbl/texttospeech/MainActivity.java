package com.rbl.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    Button btn;
    EditText text;
    TextToSpeech tts;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
        text = findViewById(R.id.vtext);
        tts = new TextToSpeech(getBaseContext(), this);
        tts.setLanguage(Locale.getDefault());
    }

    @Override
    public void onClick(View v) {
        String data = text.getText().toString();
        int code = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
        onInit(code);
    }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}