package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        ImageButton loginPageBtn,regPageBtn;
        SQLiteDB sqLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqLiteDB = new SQLiteDB(this);
        List<User> usersCheck = sqLiteDB.getAuthenData();
        if (usersCheck.toArray().length != 0){
            startActivity(new Intent(getApplication(),Info.class));
        }else{
            setContentView(R.layout.activity_main);
            init();
        }


    }

    private void init() {
        loginPageBtn = findViewById(R.id.loginPageBtn);
        loginPageBtn.setOnClickListener(this);
        regPageBtn = findViewById(R.id.regPageBtn);
        regPageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.loginPageBtn :
                intent = new Intent(getApplicationContext(),Login.class);
                break;
            case R.id.regPageBtn :
                intent = new Intent(getApplicationContext(),Registration.class);
                break;
        }

        startActivity(intent);



    }
}



