package com.jenbumapps.e_passbordumsa_admin;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.e_passbordumsa_admin.app.App;
import com.jenbumapps.e_passbordumsa_admin.frag.ApprovedFrag;
import com.jenbumapps.e_passbordumsa_admin.frag.NewReqFrag;
import com.jenbumapps.e_passbordumsa_admin.frag.SettingsFrag;
import com.jenbumapps.e_passbordumsa_admin.utility.Utility;

import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends FragmentActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();
    private BottomNavigationView btmNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btmNavigation = findViewById(R.id.navigation);
        btmNavigation.setOnNavigationItemSelectedListener(bottomNavigationAction());

        btmNavigation.setSelectedItemId(R.id.nav_new_req);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationAction() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return loadFragments(menuItem.getItemId());
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.app_name);

        if(App.globalParams.size()==0) {
            fetchGlobalParams();
        }
    }

    private void fetchGlobalParams() {
        Dialog dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(true)
                .setMessage("Fetching global parameters")
                .build();
        dialog.show();
        ApiManager.globalParam().fetchAll().enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body() != null) {
                        App.globalParams = response.body();
                    } else {
                        Toast.makeText(DashboardActivity.this, "Error fetching global param. Please restart the application", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(DashboardActivity.this, "Failed to fetch global param", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean loadFragments(int navItemId) {
        Fragment fragment;

        switch (navItemId) {
            case R.id.nav_new_req:
                fragment = new NewReqFrag();
                break;

            case R.id.nav_settings:
                fragment = new SettingsFrag();
                break;
            default:
                fragment = new ApprovedFrag();
                break;
        }

        // load fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_dashboard, fragment)
                .commit();
        return true;
    }

    @Override
    public void onBackPressed() {

        Utility.exitApp(this);
    }
}