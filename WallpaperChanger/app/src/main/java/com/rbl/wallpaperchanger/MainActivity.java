package com.rbl.wallpaperchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    int[] ia;
    boolean running;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        running = false;
        ia = new int[]{
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4
        };
    }

    @Override
    public void onClick(View v) {
        if(!running){
            running=true;
            new Timer().schedule(new MyTimer(),0,3000);
        }
    }

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            WallpaperManager wm = WallpaperManager.getInstance(getBaseContext());
            Random rand = new Random();
            try {
                wm.setBitmap(BitmapFactory.decodeResource(getResources(),ia[rand.nextInt(4)]));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}