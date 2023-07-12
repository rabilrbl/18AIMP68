package com.rbl.simpcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.ctext);
        text.setText("");
    }

    private void calc(String data, String op){
        String[] ops = data.split(Pattern.quote(op));
        double op1 = Double.parseDouble(ops[0]);
        double op2 = Double.parseDouble(ops[1]);
        double ans = 0.0;
        switch (op) {
            case "+":
                ans = op1 + op2;
                break;
            case "-":
                ans = op1 - op2;
                break;
            case "*":
                ans = op1 * op2;
                break;
            case "/":
                ans = op1 / op2;
                break;
        }
        text.setText(String.valueOf(ans));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.beq){
            String data = text.getText().toString();
            if(data.contains("+")) {
                calc(data, "+");
            }if(data.contains("-")) {
                calc(data, "-");
            }
            if(data.contains("*")) {
                calc(data, "*");
            }
            if(data.contains("/")) {
                calc(data, "/");
            }

        } else if(id == R.id.bc) {
            text.setText("");
        } else {
            Button btn = findViewById(id);
            String txt = btn.getText().toString();
            text.append(txt);
        }

    }
}