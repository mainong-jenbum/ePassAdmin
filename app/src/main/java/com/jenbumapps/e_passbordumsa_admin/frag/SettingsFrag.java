package com.jenbumapps.e_passbordumsa_admin.frag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.core.memory.SharedPrefManager;
import com.jenbumapps.core.model.User;
import com.jenbumapps.e_passbordumsa_admin.LoginActivity;
import com.jenbumapps.e_passbordumsa_admin.R;
import com.jenbumapps.e_passbordumsa_admin.app.App;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFrag extends Fragment implements View.OnClickListener {
    private FragmentActivity mContext;

    private EditText etPass1;
    private EditText etPass2;
    private Button btnUpdatePassword;
    private TextView tvLogout;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentActivity) {
            mContext = (FragmentActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initView(view);

        initListener();

        return view;
    }

    private void initView(View view) {
        tvLogout = view.findViewById(R.id.tv_logout);
        etPass1 = view.findViewById(R.id.et_pass1);
        etPass2 = view.findViewById(R.id.et_pass2);
        btnUpdatePassword = view.findViewById(R.id.btn_update_password);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = mContext.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContext, R.color.md_blue_grey_700));
        }
    }

    private void initListener() {
        tvLogout.setOnClickListener(this);
        btnUpdatePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_logout) {
            SharedPrefManager.getInstance(mContext).removeUserPref();
            startActivity(new Intent(mContext, LoginActivity.class));
            mContext.finish();
        } else if(v.getId() == R.id.btn_update_password) {
            updatePassword();
        }
    }

    private void updatePassword() {
        String pass1 = etPass1.getText().toString().trim();
        if(pass1.length() <6) {
            Toast.makeText(mContext, "The password should be a minimum of 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass2 = etPass2.getText().toString().trim();
        if(pass1.equals(pass2)){
            Dialog dialog = new SpotsDialog.Builder()
                    .setContext(mContext)
                    .setMessage("Updating password")
                    .build();

            dialog.show();
            ApiManager.user().updatePassword(App.user.getId(), pass2).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    dialog.dismiss();
                    if(response.isSuccessful()) {
                        if(response.body() != null) {
                            App.user = response.body();
                            Toast.makeText(mContext, "Password updated!", Toast.LENGTH_SHORT).show();
                            etPass1.setText("");
                            etPass2.setText("");
                        } else {
                            Toast.makeText(mContext, "Failed to update password. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Failed to update password. Try again!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                    Toast.makeText(mContext, "Server error. Try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(mContext, "The passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }
}
