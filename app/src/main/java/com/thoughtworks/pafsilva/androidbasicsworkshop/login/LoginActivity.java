package com.thoughtworks.pafsilva.androidbasicsworkshop.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private EditText edtEmail;

    private EditText edtPassword;

    private Call<UserInfo> userInfoCall;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("LoginActivity Lifecycle", "onCreate");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
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
        if (userInfoCall.isExecuted()) {
            userInfoCall.cancel();
            Toast.makeText(LoginActivity.this, "Cancelamos Ejecucion", Toast.LENGTH_SHORT).show();
        }

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

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.trim().length() != 0 || password.trim().length() != 0) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMax(100);
            progressDialog.setMessage("Scaneando el Ki  ....");
            progressDialog.setTitle("Verificando KI");
            progressDialog.show();

            callLoginService(email, password);
        } else {
            Toast.makeText(LoginActivity.this, "Se deben ingresar los valores", Toast.LENGTH_SHORT).show();
        }
    }

    private void callLoginService(String email, String password) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mobilemock.uat.lan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        LoginEndpoints APIEndpoints = retrofit.create(LoginEndpoints.class);
        userInfoCall = APIEndpoints.getUser(email, password);

        userInfoCall.enqueue(new Callback<UserInfo>() {

            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                //On Sucess

                if (response.code() == 200) {

                    Intent navigationIntent = new Intent(LoginActivity.this, UserDetailsActivity.class);
                    navigationIntent.putExtra("user", response.body());
                    startActivity(navigationIntent);

                    progressDialog.dismiss();
                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Error al Llamar Servicio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
