package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;

import java.util.List;

public class Info extends AppCompatActivity implements View.OnClickListener {
    SQLiteDB sqLiteDB;
    TextView textUsername,textName,textEmail;
    ImageButton scanBtn,logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomation_activity);
        sqLiteDB = new SQLiteDB(this);
        textEmail = findViewById(R.id.TextEmail);
        textName = findViewById(R.id.TextName);
        textUsername = findViewById(R.id.TextUsername);
        logoutBtn = findViewById(R.id.logout);
        List<User> users = sqLiteDB.getAuthenData();
        for (User u : users){
            textUsername.setText(u.getUsername());
            textName.setText(u.getName());
            textEmail.setText(u.getEmail());
        }
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),QrScanner.class));
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDB.dropTable();
                startActivity(new Intent(getApplication(),MainActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {


    }
}
