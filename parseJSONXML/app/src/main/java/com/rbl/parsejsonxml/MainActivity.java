package com.rbl.parsejsonxml;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnjson, btnxml;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnjson = findViewById(R.id.btnjson);
        btnxml = findViewById(R.id.btnxml);
        btnxml.setOnClickListener(this);
        btnjson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnjson)){
            Intent it = new Intent(this, DataParser.class);
            it.putExtra("mode", "json");
            startActivity(it);
        } else {
            Intent it = new Intent(this, DataParser.class);
            it.putExtra("mode", "xml");
            startActivity(it);
        }
    }
}