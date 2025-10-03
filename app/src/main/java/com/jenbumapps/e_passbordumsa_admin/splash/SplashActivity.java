package com.jenbumapps.e_passbordumsa_admin.splash;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.core.memory.SharedPrefManager;
import com.jenbumapps.core.model.User;
import com.jenbumapps.e_passbordumsa_admin.DashboardActivity;
import com.jenbumapps.e_passbordumsa_admin.LoginActivity;
import com.jenbumapps.e_passbordumsa_admin.R;
import com.jenbumapps.e_passbordumsa_admin.app.App;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jenbumapps.e_passbordumsa_admin.app.App.user;

public class SplashActivity extends AppCompatActivity{
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ffffff"));
        }

        fetchGlobalParams();
        checkPermissions();
    }


    /**
     * Starts Login Activity
     */
    private void startLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void fetchGlobalParams() {
        ApiManager.globalParam().fetchAll().enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        App.globalParams.clear();
                        App.globalParams.putAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "Failed to fetch global parameters", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Checks if user exists in sharedpref
     * Starts startMainActivity if the no data is found in shared preferences
     */
    private void authenticate() {
        Log.i(TAG, "Authenticating user");

        boolean loggedIn = SharedPrefManager.getInstance(getApplication()).isUserLoggedIn();
        Log.d(TAG, "isUserLoggedIn" + loggedIn);

        if (loggedIn) {
            final long phone = SharedPrefManager.getInstance(getApplication()).getUserPhone();

            Log.d(TAG, "User found in SharedPref, phone =" +phone);
            ApiManager.user().fetchByPhone(phone).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        if(response.body() != null) {

                            user = response.body();
                            checkStatus(user);
                        } else {
                            startLoginActivity();
                            Toast.makeText(SplashActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SplashActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                        startLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    startLoginActivity();
                    Toast.makeText(SplashActivity.this, "Server Error.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            startLoginActivity();
        }
    }

    private void checkStatus(User user) {
        Log.d("USER ACCOUNT STATUS", String.valueOf(user.getStatus()));
        switch (user.getStatus()){

            case INACTIVE:
                Toast.makeText(this, "Account has been suspended. Please contact admin!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case ACTIVE:
                if(user.getPhone() != 0) {
                    startDashboard();
                } else {
                    startLoginActivity();
                }
                break;
            case SELF_DEACTIVATE:
                Toast.makeText(this, "Account has been self deactivated. Please contact admin to reactivate!", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    private void startDashboard() {
        Log.i("startDashboard ","Called");
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * Check app permissions
     * STORAGE
     * LOCATION
     * CAMERA
     */
    private void checkPermissions() {
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("PERMISSION", "GRANTED");
                            authenticate();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permanently, navigate user to app settings
                            Log.d("PERMISSION", "DENIED");
                            Toast.makeText(SplashActivity.this, "Please provide permission first", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

}
