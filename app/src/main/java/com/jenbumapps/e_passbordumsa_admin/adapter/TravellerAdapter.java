package com.jenbumapps.e_passbordumsa_admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.model.Traveller;
import com.jenbumapps.e_passbordumsa_admin.R;

import java.util.List;

public class TravellerAdapter extends RecyclerView.Adapter<TravellerAdapter.AdapterVh> {

    public interface Listener{
        void onTravellerDelete(Traveller traveller, int pos);
        void onTravellerSelected(Traveller traveller, int pos);

    }

    private List<Traveller> travellers;
    private Listener listener;
    private boolean modifiable;

    public TravellerAdapter(List<Traveller> travellers, Listener listener, boolean modifiable) {
        this.travellers = travellers;
        this.listener = listener;
        this.modifiable = modifiable;
    }

    @NonNull
    @Override
    public AdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_traveller_item, parent, false);

        AdapterVh vh = new AdapterVh(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTravellerSelected(travellers.get(vh.getAdapterPosition()), vh.getAdapterPosition());
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVh holder, int position) {
        holder.tvSlNo.setText(String.valueOf(position+1));
        holder.tvName.setText(travellers.get(position).getName());
        holder.tvType.setText(travellers.get(position).getType().name());

        if(!modifiable) holder.ivDelete.setVisibility(View.GONE);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTravellerDelete(travellers.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return travellers.size();
    }

    class AdapterVh extends RecyclerView.ViewHolder {

        TextView tvSlNo;
        TextView tvName;
        TextView tvType;
        ImageView ivDelete;

        public AdapterVh(@NonNull View view) {
            super(view);

            tvSlNo = view.findViewById(R.id.tv_slno);
            tvName = view.findViewById(R.id.tv_traveller_name);
            tvType = view.findViewById(R.id.tv_traveller_type);
            ivDelete = view.findViewById(R.id.iv_delete);
        }
    }
}
