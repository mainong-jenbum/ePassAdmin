package com.jenbumapps.e_passbordumsa_admin.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.model.EPass;
import com.jenbumapps.core.model.codec.ApproveStatus;
import com.jenbumapps.core.model.time.DateHelper;
import com.jenbumapps.core.model.time.LocalDateTime;
import com.jenbumapps.e_passbordumsa_admin.R;
import com.jenbumapps.e_passbordumsa_admin.app.App;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PermitAdapter extends RecyclerView.Adapter<PermitAdapter.AdapterVh> {

    public interface Listener{
        void onPermitSelected(EPass formData, int pos, View view);
    }

    private List<EPass> permits;
    private Listener mListener;

    public PermitAdapter(List<EPass> permits, Listener mListener) {
        this.permits = permits;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public AdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_permit_item, parent, false);

        final AdapterVh vh = new AdapterVh(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPermitSelected(permits.get(vh.getAdapterPosition()), vh.getAdapterPosition(), vh.ivQrCode);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVh holder, int position) {

        EPass ePass = permits.get(position);
        String idPrefix = null;
        if(App.globalParams != null)
            idPrefix = App.globalParams.get("KEY_ID_PREFIX");

        if(idPrefix == null) idPrefix = "BSA(COVID-19)/2020/";

        if(ePass.getStatus() == ApproveStatus.REJECTED) {
            holder.cvRejected.setVisibility(View.VISIBLE);
            holder.cvApproved.setVisibility(View.GONE);

            holder.tvRejectId.setText(String.format(Locale.ENGLISH,"#%s%d", idPrefix, ePass.getId()));
            if(URLUtil.isValidUrl(ePass.getQrCodeUrl()))
                Picasso.get().load(ePass.getQrCodeUrl()).into(holder.ivRejectQr);
            holder.tvReason.setText(ePass.getRejectReason());

            return;
        }

        Calendar expiry = ePass.getDateOfJourney().getDate().getDate();
        expiry.set(Calendar.HOUR_OF_DAY, ePass.getDateOfJourney().getTime().getHour());
        expiry.set(Calendar.MINUTE, ePass.getDateOfJourney().getTime().getMinute());
        expiry.add(Calendar.HOUR_OF_DAY, 8);

        if(Calendar.getInstance().after(expiry)) {
            Picasso.get().load(R.drawable.expired).into(holder.ivQrCode);
        } else {
            if(URLUtil.isValidUrl(ePass.getQrCodeUrl()))
                Picasso.get().load(ePass.getQrCodeUrl()).into(holder.ivQrCode);
        }

        holder.tvRoute.setText(ePass.getRouteOfJourney());

        String doj = DateHelper.formatDate(ePass.getDateOfJourney()) +" "+ DateHelper.formatTime(ePass.getDateOfJourney());
        holder.tvDate.setText(doj);

        holder.tvRefId.setText(String.format(Locale.ENGLISH,"#%s%d", idPrefix, ePass.getId()));

        holder.tvTravellerNo.setText(String.valueOf(ePass.getTravellers().size()));
        holder.tvApplicantName.setText(ePass.getApplicantDetail().getName());


        // Debugging date of journey
        String dateNow = DateHelper.formatDate(LocalDateTime.of(Calendar.getInstance()));
        Log.d("PermitAdapter","Date now- "+dateNow);
        String expiryDoj = DateHelper.formatDate(LocalDateTime.of(expiry)) + " " +DateHelper.formatTime(LocalDateTime.of(expiry));
        Log.d("PermitAdapter","Doj expiry- "+expiryDoj);
        Log.d("PermitAdapter","DOJ- "+doj);

    }

    @Override
    public int getItemCount() {
        return permits.size();
    }

    class AdapterVh extends RecyclerView.ViewHolder {

        ImageView ivQrCode;
        TextView tvRoute;
        TextView tvDate;
        TextView tvRefId;
        TextView tvTravellerNo;
        TextView tvApplicantName;

        CardView cvApproved;
        CardView cvRejected;
        TextView tvRejectId;
        ImageView ivRejectQr;
        TextView tvReason;

        public AdapterVh(@NonNull View view) {
            super(view);

            ivQrCode = view.findViewById(R.id.iv_qr_code);
            tvRoute = view.findViewById(R.id.tv_route);
            tvDate = view.findViewById(R.id.tv_date);
            tvRefId = view.findViewById(R.id.tv_ref_no);
            tvTravellerNo = view.findViewById(R.id.tv_traveller_no);
            tvApplicantName = view.findViewById(R.id.tv_applicant_name);
        }
    }
}
