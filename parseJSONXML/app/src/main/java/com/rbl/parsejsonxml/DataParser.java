package com.rbl.parsejsonxml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DataParser extends AppCompatActivity {
    TextView xmlView, jsonView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parser);
        xmlView = findViewById(R.id.xmlView);
        jsonView = findViewById(R.id.jsonView);

        String mode = getIntent().getStringExtra("mode");
        if (mode.equals("json")){
            try {
                parseJSON();
            } catch (Exception e) {
                e.printStackTrace();
            }
        ;
    }else {
            try {
                parseXML();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    void parseJSON() throws IOException, JSONException {
        InputStream inputStream = getAssets().open("Input.json");
        byte[] data = new byte[inputStream.available()];
        inputStream.read(data);
        String strdata = new String(data);
        JSONObject jsnp = new JSONObject(strdata).getJSONObject("employee");
        String output = "";
        output += "CITY: "+ jsnp.getString("city_name") +"\n";
        output += "Latitude: "+ jsnp.getString("Latitude") +"\n";
        output += "Longitude: "+ jsnp.getString("Longitude") + "\n";
        output += "Temperature: "+ jsnp.getString("Temperature") + "\n";
        output += "Humidity: "+ jsnp.getString("Humidity") + "\n";

        jsonView.setText(output);
    }

    void parseXML() throws IOException, ParserConfigurationException, SAXException {
        InputStream inputStream = getAssets().open("Input.xml");
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(inputStream);
        Element emp = (Element) doc.getElementsByTagName("employee").item(0);
        String[] emps = emp.getTextContent().replaceAll(" ","").split("\n");
        String output = "";
        output += "City: "+ emps[1] + "\n";
        output += "Latitude: "+emps[2]+"\n";
        output+= "Longitude: "+emps[3]+"\n";
        output+= "Temperature: "+emps[4]+"\n";
        output += "Humidity: "+emps[5]+"\n";

        xmlView.setText(output);
    }
}