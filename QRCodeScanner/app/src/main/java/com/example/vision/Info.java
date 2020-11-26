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
        sqLiteDB = new SQLiteDB(this);
        List<User> usersCheck = sqLiteDB.getAuthenData();
        if (usersCheck.toArray().length == 0){
            startActivity(new Intent(getApplication(),MainActivity.class));
        }else{
            setContentView(R.layout.infomation_activity);
            init();
        }



    }

    public void init(){
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
        scanBtn.setOnClickListener(v -> startActivity(new Intent(getApplication(), QrScannerForVerify.class)));

        logoutBtn.setOnClickListener(v -> {
            sqLiteDB.dropTable();
            startActivity(new Intent(getApplication(),MainActivity.class));
        });
    }

    @Override
    public void onClick(View v) {


    }
}
