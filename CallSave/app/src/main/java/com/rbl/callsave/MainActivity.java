package com.rbl.callsave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.vtext);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String data = text.getText().toString();
        if(id == R.id.bdel){
            if(data.length() > 0)
            text.setText(data.substring(0, data.length()-1));
            else {
                text.setText("");
            }
        } else if(id == R.id.bcall){
            Intent it = new Intent(Intent.ACTION_DIAL);
            it.setData(Uri.parse("tel:"+data));
            startActivity(it);
        } else if(id == R.id.bsave){
            Intent it = new Intent(ContactsContract.Intents.Insert.ACTION);
            it.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            it.putExtra(ContactsContract.Intents.Insert.NAME,"Unknown");
            it.putExtra(ContactsContract.Intents.Insert.PHONE,data);
            startActivity(it);
        } else {
            Button btn = findViewById(id);
            text.append(btn.getText().toString());
        }
    }
}