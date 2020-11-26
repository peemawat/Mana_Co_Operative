package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        ImageButton loginPageBtn,scanQrBtn;
        SQLiteDB sqLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();



    }

    private void init() {
        loginPageBtn = findViewById(R.id.loginPageBtn);
        loginPageBtn.setOnClickListener(this);
        scanQrBtn = findViewById(R.id.scanQrBtn);
        scanQrBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.loginPageBtn :
                intent = new Intent(getApplicationContext(),Login.class);
                break;
            case R.id.scanQrBtn :
                intent = new Intent(getApplicationContext(), QrScannerForMainPage.class);
                break;
        }

        startActivity(intent);



    }
}



