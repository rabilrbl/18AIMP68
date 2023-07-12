package com.rbl.loginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView text;
    EditText usrn, pswd;
    Button button;
    
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        text = findViewById(R.id.vtext);
        text.setText("Login");
        button = findViewById(R.id.btn);
        button.setText("Login");
        usrn = findViewById(R.id.username);
        pswd = findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Bundle bd = getIntent().getBundleExtra("data");
                String ActualUsername = bd.getString("username");
                String ActualPassword = bd.getString("password");
                String username = usrn.getText().toString();
                String password = pswd.getText().toString();
                if(username.equals(ActualUsername) && password.equals(ActualPassword)){
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    count++;
                    if(count == 3) {
                        button.setEnabled(false);
                        Toast.makeText(LoginActivity.this, "Failed Login attempt!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
