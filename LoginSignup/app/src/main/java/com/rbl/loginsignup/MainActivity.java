package com.rbl.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView text;
    EditText usrn, pswd;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.vtext);
        text.setText("Signup");
        button = findViewById(R.id.btn);
        button.setText("Signup");
        usrn = findViewById(R.id.username);
        pswd = findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = usrn.getText().toString();
                String password = pswd.getText().toString();
                if(validatePassword(password)){
                    Bundle bd = new Bundle();
                    bd.putString("username", username);
                    bd.putString("password", password);
                    Intent it = new Intent(MainActivity.this, LoginActivity.class);
                    it.putExtra("data", bd);
                    startActivity(it);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validatePassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(password);
        return match.matches();
    }
}