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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.core.model.EPass;
import com.jenbumapps.e_passbordumsa_admin.PermitDetailActivity;
import com.jenbumapps.e_passbordumsa_admin.R;
import com.jenbumapps.e_passbordumsa_admin.adapter.PermitAdapter;
import com.jenbumapps.e_passbordumsa_admin.app.App;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReqFrag extends Fragment implements PermitAdapter.Listener {

    private TextView tvNoItem;
    private RecyclerView rvPermit;
    private PermitAdapter permitAdapter;

    private List<EPass> permits = new ArrayList<>();

    private FragmentActivity mContext;
    private Timer timer;

    // Header
    private TextView tvAuthorityDesignationAbbr;
    private TextView tvAuthorityOfficeName;
    private TextView tvAuthorityOfficeAddress;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  FragmentActivity) {
            mContext =(FragmentActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_permit_request_list, container, false);

        tvNoItem = view.findViewById(R.id.tv_no_item);
        rvPermit = view.findViewById(R.id.rv_request);

        permitAdapter = new PermitAdapter(permits, this);
        rvPermit.setAdapter(permitAdapter);
        rvPermit.setLayoutManager(new LinearLayoutManager(mContext));

        fetchPermitRequests();

        // Header
        tvAuthorityDesignationAbbr = view.findViewById(R.id.tv_authority_designation_abbr);
        tvAuthorityOfficeName = view.findViewById(R.id.tv_authority_office_name);
        tvAuthorityOfficeAddress = view.findViewById(R.id.tv_authority_office_address);
        checkData();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = mContext.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        }
        return view;
    }

    private void checkData() {

        // Header
        tvAuthorityOfficeName.setText(String.format(Locale.ENGLISH,"OFFICE OF THE %s", App.user.getDesignation()));
        tvAuthorityDesignationAbbr.setText(App.user.getDesignationAbbr());
        tvAuthorityOfficeAddress.setText(App.user.getAddress());
    }

    private void fetchPermitRequests() {
        ApiManager.form().fetchNewRequests(App.user.getCity().getId()).enqueue(new Callback<List<EPass>>() {
            @Override
            public void onResponse(Call<List<EPass>> call, Response<List<EPass>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        permits.clear();
                        permits.addAll(response.body());
                        if(permits.size()>0) {
                            tvNoItem.setVisibility(View.GONE);
                            rvPermit.setVisibility(View.VISIBLE);
                            permitAdapter.notifyDataSetChanged();
                        } else {
                            tvNoItem.setVisibility(View.VISIBLE);
                            rvPermit.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(mContext, "Failed to fetch new requests", Toast.LENGTH_SHORT).show();
                    tvNoItem.setVisibility(View.VISIBLE);
                    rvPermit.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<EPass>> call, Throwable t) {
                t.printStackTrace();
                tvNoItem.setVisibility(View.VISIBLE);
                rvPermit.setVisibility(View.GONE);
                Toast.makeText(mContext, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPermitSelected(EPass formData, int pos, View view) {

        Intent intent = new Intent(mContext, PermitDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("PERMIT", Parcels.wrap(formData));
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, view,
                "qr_code");

        startActivity(intent, options.toBundle());

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(mContext, "Looking for new request", Toast.LENGTH_SHORT).show();
        timer = new Timer();
        TimerTask orderWaitTask = new TimerTask() {
            @Override
            public void run() {
                fetchPermitRequests();
            }
        };

        timer.schedule (orderWaitTask, 0L, 10000);  //10 sec
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        timer = null;
    }
}
