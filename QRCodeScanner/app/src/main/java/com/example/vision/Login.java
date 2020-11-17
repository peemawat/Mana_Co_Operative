package com.example.vision;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgBtnSubmit,loginBtnBack;
    EditText edtUsername,edtPassword;
    SQLiteDB sqlHelper;
    private boolean CheckEditTextEmpty, CheckCompareData = false;
    private String username,password;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        imgBtnSubmit = findViewById(R.id.imgBtnSubmit);
        imgBtnSubmit.setOnClickListener(this);
        edtUsername = findViewById(R.id.editTextLoginUsername);
        edtPassword = findViewById(R.id.editTextLoginPassword);
        loginBtnBack = findViewById(R.id.loginBackBtn);
        apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        loginBtnBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplication(),MainActivity.class));
        });
        sqlHelper = new SQLiteDB(this);


    }

    @Override
    public void onClick(View v) {
        MD5 md5 = new MD5();
        this.username = edtUsername.getText().toString();
        this.password = md5.getMd5(edtPassword.getText().toString());
        CheckEditTextIsEmptyOrNot(this.username,this.password);
        if (CheckEditTextEmpty  == true){
            Toast.makeText(this,"Please enter your username and password", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Invalid please try again!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), Login.class));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }else{
            getDataFromMongo(this.username,this.password);

        }


    }

    public void CheckEditTextIsEmptyOrNot(String username, String password){

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            CheckEditTextEmpty = true ; }
        else {
            CheckEditTextEmpty = false ; }
    }

    public void getDataFromMongo(String username,String password) {
        Call<List<User>> callList = apiInterface.getUser();
        callList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    response.body().forEach(user -> compareUser(user, username, password));


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG","Failed");


            }
        });
    }

    public void compareUser(User user, String username, String password){
        if (user.getUsername().equalsIgnoreCase(username)&&user.getPassword().equals(password)){
            sqlHelper.insertData2SQLite(new User(user.get_id(),user.getName(),user.getEmail(),user.getUsername(),user.getPassword()));
            startActivity(new Intent(getApplication(),Info.class));
        }else{
            /**
             * haven't thing to do
             */
        }

    }
}
