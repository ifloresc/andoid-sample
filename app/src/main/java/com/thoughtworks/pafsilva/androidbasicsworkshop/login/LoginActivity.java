package com.thoughtworks.pafsilva.androidbasicsworkshop.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.thoughtworks.pafsilva.androidbasicsworkshop.R;
import com.thoughtworks.pafsilva.androidbasicsworkshop.login.service.LoginEndpoints;
import com.thoughtworks.pafsilva.androidbasicsworkshop.models.UserInfo;
import com.thoughtworks.pafsilva.androidbasicsworkshop.userdetails.UserDetailsActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("LoginActivity Lifecycle", "onCreate");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LoginActivity Lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LoginActivity Lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LoginActivity Lifecycle", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LoginActivity Lifecycle", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LoginActivity Lifecycle", "onRestart");
    }

    @Override
    public void onClick(View view) {
        Intent navigationIntent = new Intent(this, UserDetailsActivity.class);
        startActivity(navigationIntent);
    }

    private void callLoginService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        LoginEndpoints APIEndpoints = retrofit.create(LoginEndpoints.class);
        Call<UserInfo> userInfoCall = APIEndpoints.getUser("email", "pass");

        userInfoCall.enqueue(new Callback<UserInfo>() {

            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                //On Sucess
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                //On Failure
            }
        });
    }
}
