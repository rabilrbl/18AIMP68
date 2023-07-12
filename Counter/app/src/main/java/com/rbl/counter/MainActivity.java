package com.rbl.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bstart, bstop;
    boolean running;
    int counter;
    TextView text;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        running=false;
        counter=0;
        bstart = findViewById(R.id.bstart);
        bstart.setOnClickListener(this);
        bstop = findViewById(R.id.bstop);
        bstop.setOnClickListener(this);
        text = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bstart)){
            running=true;
            counter=0;
            new Counter().start();
            bstart.setEnabled(false);
            bstop.setEnabled(true);
        } else if (v.equals(bstop)){
            running=false;
            bstart.setEnabled(true);
            bstop.setEnabled(false);
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            text.setText(String.valueOf(msg.what));
        }
    };

    class Counter extends Thread {
        @Override
        public void run() {
            while(running){
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}