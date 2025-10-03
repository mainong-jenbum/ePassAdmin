package com.jenbumapps.e_passbordumsa_admin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.api.ApiManager;
import com.jenbumapps.core.model.EPass;
import com.jenbumapps.core.model.EPassTerm;
import com.jenbumapps.core.model.File;
import com.jenbumapps.core.model.Traveller;
import com.jenbumapps.core.model.codec.ApproveStatus;
import com.jenbumapps.core.model.time.DateHelper;
import com.jenbumapps.e_passbordumsa_admin.adapter.FileAdapter;
import com.jenbumapps.e_passbordumsa_admin.adapter.TermsAdapter;
import com.jenbumapps.e_passbordumsa_admin.adapter.TravellerAdapter;
import com.jenbumapps.e_passbordumsa_admin.dialog.RejectReasonDialog;
import com.jenbumapps.e_passbordumsa_admin.qrcode.QRCodeGenerator;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermitDetailActivity extends AppCompatActivity implements TravellerAdapter.Listener, View.OnClickListener, FileAdapter.Listener {

    private TextView tvPermitId;
    private TextView tvPermitApprovalDate;
    private TextView tvPermissionDetail;
    private RecyclerView rvTravellers;
    private TextView tvDoj;
    private TextView tvVehicleRc;
    private TextView tvDriverName;
    private TextView tvDriverContact;
    private TextView tvRoute;
    private RecyclerView rvTerms;
    private ImageView ivQrCode;
    private ImageView ivAuthSign;
    private TextView tvAuthName;
    private TextView tvAuthDesignation;
    private TextView tvAuthAddress;
    private ImageView ivApprove;
    private TextView tvCity;

    private Button btnApprove;
    private Button btnReject;

    private LinearLayout llContainerAuthority;
    private LinearLayout llContainerAction;

    private CardView cvSupportingDoc;
    private RecyclerView rvDocs;

    // Adapters
    private TermsAdapter termsAdapter;
    private TravellerAdapter travellerAdapter;
    private FileAdapter fileAdapter;

    // List
    private List<EPassTerm> terms = new ArrayList<>();
    private List<Traveller> travellers = new ArrayList<>();
    private List<File> files = new ArrayList<>();
    private EPass formData;

    // Header
    private TextView tvAuthorityDesignationAbbr;
    private TextView tvAuthorityOfficeName;
    private TextView tvAuthorityOfficeAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_e_pass_detail);

        initView();
        initListener();
        initAdapter();

        checkData();

    }

    private void initListener() {
        btnApprove.setOnClickListener(this);
        btnReject.setOnClickListener(this);
        ivQrCode.setOnClickListener(this);
    }

    private void initView() {
        tvCity = findViewById(R.id.tv_city);
        ivApprove = findViewById(R.id.iv_approved);
        tvPermitId = findViewById(R.id.tv_permit_id);
        tvPermitApprovalDate = findViewById(R.id.tv_permit_approval_date);
        tvPermissionDetail = findViewById(R.id.tv_permit_detail);
        rvTravellers = findViewById(R.id.rv_traveller);
        tvDoj = findViewById(R.id.tv_doj);
        tvVehicleRc = findViewById(R.id.tv_vehicle_rc);
        tvDriverName = findViewById(R.id.tv_driver_name);
        tvDriverContact = findViewById(R.id.tv_driver_contact);
        tvRoute = findViewById(R.id.tv_route);
        rvTerms = findViewById(R.id.rv_terms);
        ivQrCode = findViewById(R.id.iv_qr_code);
        ivAuthSign = findViewById(R.id.iv_authority_sign);
        tvAuthName = findViewById(R.id.tv_authority_name);
        tvAuthDesignation = findViewById(R.id.tv_authority_designation);
        tvAuthAddress = findViewById(R.id.tv_authority_address);

        // Header
        tvAuthorityDesignationAbbr = findViewById(R.id.tv_authority_designation_abbr);
        tvAuthorityOfficeName = findViewById(R.id.tv_authority_office_name);
        tvAuthorityOfficeAddress = findViewById(R.id.tv_authority_office_address);


        btnApprove = findViewById(R.id.btn_approve);
        btnReject = findViewById(R.id.btn_reject);

        llContainerAction = findViewById(R.id.ll_container_action);
        llContainerAuthority = findViewById(R.id.ll_container_authority);

        rvDocs = findViewById(R.id.rv_doc);
        cvSupportingDoc = findViewById(R.id.cv_supporting_doc);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }

    private void initAdapter() {
        travellerAdapter = new TravellerAdapter(travellers, this, false);
        rvTravellers.setAdapter(travellerAdapter);
        rvTravellers.setLayoutManager(new LinearLayoutManager(this));

        termsAdapter = new TermsAdapter(terms);
        rvTerms.setAdapter(termsAdapter);
        rvTerms.setLayoutManager(new LinearLayoutManager(this));

        fileAdapter = new FileAdapter(files, this, false);
        rvDocs.setAdapter(fileAdapter);
        rvDocs.setLayoutManager(new LinearLayoutManager(this));

    }

    private void checkData() {
        formData = Parcels.unwrap(getIntent().getParcelableExtra("PERMIT"));

        if(formData != null) {
            setData(formData);
            return;
        }

        Toast.makeText(this, "Invalid Permit Data. Try again!", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void setData(EPass data) {

        // Header
        tvAuthorityOfficeName.setText(String.format(Locale.ENGLISH,"OFFICE OF THE %s", data.getAuthorityDetail().getAuthorityDesignation()));
        tvAuthorityDesignationAbbr.setText(data.getAuthorityDetail().getAuthorityDesignationAbbr());
        tvAuthorityOfficeAddress.setText(data.getAuthorityDetail().getAuthorityAddress());

        if(data.getStatus() == ApproveStatus.APPROVED) {
            ivApprove.setVisibility(View.VISIBLE);
            llContainerAuthority.setVisibility(View.VISIBLE);
            llContainerAction.setVisibility(View.GONE);
            tvAuthName.setText(data.getAuthorityDetail().getAuthorityName());
            tvAuthDesignation.setText(data.getAuthorityDetail().getAuthorityDesignation());
            tvAuthAddress.setText(data.getAuthorityDetail().getAuthorityAddress());
            if(URLUtil.isValidUrl(data.getAuthorityDetail().getAuthoritySign())) {
                Picasso.get().load(data.getAuthorityDetail().getAuthoritySign()).into(ivAuthSign);
            }
        } else {
            ivApprove.setVisibility(View.GONE);
            llContainerAuthority.setVisibility(View.GONE);
            llContainerAction.setVisibility(View.VISIBLE);
        }

        tvPermitId.setText(String.format(Locale.ENGLISH,"%s%d", data.getIdPrefix(), data.getId()));

        String approvalDate = "NOT APPROVED";
        if(data.getPermitApprovalDate()!= null) {
            approvalDate = "Dated "+data.getCity().getName()+", "+DateHelper.formatDate(data.getPermitApprovalDate().getDate().getTime());
        }
        tvPermitApprovalDate.setText(approvalDate);

        tvPermissionDetail.setText(data.getPermissionDetail());

        Calendar expiry = data.getDateOfJourney().getDate().getDate();
        expiry.set(Calendar.HOUR_OF_DAY, data.getDateOfJourney().getTime().getHour());
        expiry.set(Calendar.MINUTE, data.getDateOfJourney().getTime().getMinute());
        expiry.add(Calendar.HOUR_OF_DAY, 8);
        if(Calendar.getInstance().after(expiry)) {
            Picasso.get().load(R.drawable.expired).into(ivQrCode);
        } else {
            if(URLUtil.isValidUrl(data.getQrCodeUrl()))
                Picasso.get().load(data.getQrCodeUrl()).into(ivQrCode);
        }

        String doj = DateHelper.formatDate(data.getDateOfJourney()) + " " + DateHelper.formatTime(data.getDateOfJourney());
        tvDoj.setText(doj);
        tvVehicleRc.setText(data.getVehicleRcNumber());
        tvDriverName.setText(data.getDriverName());
        tvDriverContact.setText(String.valueOf(data.getDriverContact()));
        tvRoute.setText(data.getRouteOfJourney());
        tvCity.setText(data.getCity().getName());

        travellers.clear();
        travellers.addAll(data.getTravellers());
        travellerAdapter.notifyDataSetChanged();

        terms.clear();
        terms.addAll(data.getTermsAndConditions());
        termsAdapter.notifyDataSetChanged();

        files.clear();
        files.addAll(data.getDocumentDetail().getOtherSupportingDocuments());
        if(files.size()>0) {
            cvSupportingDoc.setVisibility(View.VISIBLE);
            fileAdapter.notifyDataSetChanged();
        } else {
            cvSupportingDoc.setVisibility(View.GONE);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_approve:
                approvePermit();
                break;

            case R.id.btn_reject:

                Dialog dialog = new RejectReasonDialog(this)
                        .setTitle("Reason")
                        .setMessage("Please mention a Reason for rejecting the permit.")
                        .setListener(new RejectReasonDialog.DialogListener() {
                            @Override
                            public void onApprove(String reason) {
                                rejectPermit(reason);
                            }

                            @Override
                            public void onDecline() {

                            }
                        });

                dialog.show();

                break;

            case R.id.iv_qr_code:
                showImageViewActivity(formData.getQrCodeUrl());
                break;
        }
    }

    private void rejectPermit(String reason) {

        Dialog dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Please wait..")
                .build();

        dialog.show();
        ApiManager.form().reject(formData.getId(), reason).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                if(response.isSuccessful()) {

                    Toast.makeText(PermitDetailActivity.this, "Rejected", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(PermitDetailActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void approvePermit() {
        Dialog dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Please wait..")
                .build();

        dialog.show();
        ApiManager.form().approve(formData.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                if(response.isSuccessful()) {

                    Toast.makeText(PermitDetailActivity.this, "Generating QR Code", Toast.LENGTH_LONG).show();
                    QRCodeGenerator.generateQR(PermitDetailActivity.this,formData.getId(), onQRCodeSet());
                    
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                t.printStackTrace();
                Toast.makeText(PermitDetailActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private QRCodeGenerator.Listener onQRCodeSet() {
        return new QRCodeGenerator.Listener() {
            @Override
            public void onQRCodeUrlUpdated() {
                Toast.makeText(PermitDetailActivity.this, "QRCode has been generated", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
    }

    @Override
    public void onFileDeleteClicked(File file, int pos) {

    }

    @Override
    public void onFileSelected(File file, int pos) {
        showImageViewActivity(file.getUrl());
    }

    @Override
    public void onTravellerDelete(Traveller traveller, int pos) {

    }

    @Override
    public void onTravellerSelected(Traveller traveller, int pos) {
        showImageViewActivity(traveller.getIdProof());
    }

    private void showImageViewActivity(String url) {
        Intent intent = new Intent(this, ImageViewerActivity.class);

        intent.putExtra("KEY_IMAGE_URL", url);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, ivQrCode,
                "image");

        startActivity(intent, options.toBundle());
    }
}
