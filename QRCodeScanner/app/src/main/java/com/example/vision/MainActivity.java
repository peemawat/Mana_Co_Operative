package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button scanPageBtn,regPageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        scanPageBtn = findViewById(R.id.scanPageBtn);
        scanPageBtn.setOnClickListener(this);
        regPageBtn = findViewById(R.id.regPageBtn);
        regPageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.scanPageBtn :
                intent = new Intent(getApplicationContext(),QrScanner.class);
                break;
            case R.id.regPageBtn :
                intent = new Intent(getApplicationContext(),Registration.class);
                break;
        }

        startActivity(intent);



    }
}



