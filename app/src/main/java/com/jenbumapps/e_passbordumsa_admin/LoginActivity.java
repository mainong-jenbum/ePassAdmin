package com.jenbumapps.e_passbordumsa_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.core.memory.SharedPrefManager;
import com.jenbumapps.core.model.User;
import com.jenbumapps.e_passbordumsa_admin.app.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();
        initListener();
    }

    private void initView() {
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login) {
            if(validationSuccess()) {
                authenticate();
            }
        }
    }

    private boolean validationSuccess() {
        if(etPhone.getText().toString().trim().length() !=10) {
            Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Password should be greater than 5 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void authenticate() {
        long phone = Long.parseLong(etPhone.getText().toString().trim());

        String password = etPassword.getText().toString().trim();

        ApiManager.user().login(phone, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        App.user = response.body();
                        SharedPrefManager.getInstance(LoginActivity.this).saveUserPref(App.user, true);
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect credentials. Try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
