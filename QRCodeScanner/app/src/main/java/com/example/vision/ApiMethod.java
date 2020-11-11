package com.example.vision;

import android.util.Log;

import com.example.vision.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiMethod {
    private int sizeList;

    public int getSizeList() {
        return sizeList;
    }

    public void setSizeList(int sizeList) {
        this.sizeList = sizeList;
    }

    public void callGetAllUser() {
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        Call<List<User>> listCall = apiInterface.getUser();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                setSizeList(response.body().toArray().length);
                Log.i("TAG","GET method successfully");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("TAG","GET method fail");

            }
        });

    }

    public void callPostUser(String id,String name,String email,String username,String password) {
        ApiInterface apiInterface = new ApiClient().getApiClient().create(ApiInterface.class);
        apiInterface.createUser(new User(id, name, email, username, password)).enqueue(new Callback<User>() {
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
}
