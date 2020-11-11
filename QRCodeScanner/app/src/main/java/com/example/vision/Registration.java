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

public class Registration extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgBtnSubmit;
    EditText edtName,edtEmail,edtUsername,edtPassword;
    private String name,email,username,password;
    private boolean CheckEditTextEmpty;
    SQLiteDB sqLiteHelper;
    ApiInterface apiInterface;
    private int sizeList;

    public int getSizeList() {
        return sizeList;
    }

    public void setSizeList(int sizeList) {
        this.sizeList = sizeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        init();
        sqLiteHelper = new SQLiteDB(this);
        apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
    }

    private void init() {
        imgBtnSubmit = findViewById(R.id.imgBtnSubmit);
        edtName = (EditText)findViewById(R.id.editTextName);
        edtEmail = (EditText)findViewById(R.id.editTextEmail);
        edtUsername = (EditText)findViewById(R.id.editTextUsername);
        edtPassword = (EditText)findViewById(R.id.editTextPassword);
        imgBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.name = edtName.getText().toString();
        this.email = edtEmail.getText().toString();
        this.username = edtUsername.getText().toString();
        this.password = edtPassword.getText().toString();

        /**
         * Check data from edit text field is empty
         */
        CheckEditTextIsEmptyOrNot(this.name,this.email,this.username,this.password);
        if (CheckEditTextEmpty == true) {
            Toast.makeText(this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Invalid please try again!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do something
                    startActivity(new Intent(getApplication(), Registration.class));
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
            /**
             * Create Object and insert data from edit text field to SQLite
             */
            methodPostV2(this.name,this.email,this.username,this.password);

            Toast.makeText(this,"Data Submit Successfully", Toast.LENGTH_LONG).show();
            ClearEditTextAfterDoneTask();
        }

    }

    public void submitData2SQLiteDB(String id,String name,String email,String username,String password) {
        //insert to sqlite
        User userSQLite = new User(id,name,email,username,password);
        sqLiteHelper.insertData2SQLite(userSQLite);
        Log.d("TAG","Insert successfully");

    }


    public void CheckEditTextIsEmptyOrNot(String name,String email, String username, String password){

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            CheckEditTextEmpty = true ; }
        else {
            CheckEditTextEmpty = false ; }
    }

    private void ClearEditTextAfterDoneTask() {
        edtName.setText("");
        edtEmail.setText("");
        edtUsername.setText("");
        edtPassword.setText("");
    }


    public void methodPostV2(String name,String email,String username,String password) {
        Call<List<User>> listCall = apiInterface.getUser();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println("Last index : "+response.body().toArray().length);
                String idx = (response.body().toArray().length+1)+"";
                methodPost(idx,name,email,username,password);
                submitData2SQLiteDB(idx,name,email,username,password);
                Log.i("TAG","Post V.2 method successfully");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG","GET method fail");

            }
        });

    }

    public void methodPost(String id,String name,String email,String username,String password) {
        Call<User> callPost = apiInterface.createUser(new User(id, name, email, username, password));
        callPost.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "Post method successfully");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "POST method failed");
            }
        });

    }

    public boolean checkDataIfExisting(){
        return true;
    }


}
