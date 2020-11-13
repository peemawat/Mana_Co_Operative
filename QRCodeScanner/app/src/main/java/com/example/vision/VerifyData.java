package com.example.vision;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;

import java.util.List;

public class VerifyData extends AppCompatActivity implements View.OnClickListener  {
//    ApiInterface apiInterface;
    Button verifyBtn;
    SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_activity);
        verifyBtn = findViewById(R.id.verifyBtn);
        verifyBtn.setOnClickListener(this);
//        apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        sqLiteDB = new SQLiteDB(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyBtn:
                Log.d("Reading","Reading..");
                List<User> users = sqLiteDB.getAuthenData();

                for(User user : users){
                    String log = "ID : "+user.get_id()+" Username : "+user.getUsername();
                    Log.d("User",log);
                    new SendMessage().execute(user.get_id()+","+user.getUsername()+","+user.getPassword());
                }


        }
    }





}

