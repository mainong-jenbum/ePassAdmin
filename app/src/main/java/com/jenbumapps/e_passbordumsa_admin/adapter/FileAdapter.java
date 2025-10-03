package com.jenbumapps.e_passbordumsa_admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.model.File;
import com.jenbumapps.e_passbordumsa_admin.R;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.AdapterVh> {

    public interface Listener{
        void onFileDeleteClicked(File file, int pos);
        void onFileSelected(File file, int pos);
    }

    private List<File> files;
    private Listener listener;
    private boolean modifiable;

    public FileAdapter(List<File> files, Listener listener, boolean modifiable) {
        this.files = files;
        this.listener = listener;
        this.modifiable = modifiable;
    }

    @NonNull
    @Override
    public AdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_file_item, parent, false);

        AdapterVh vh = new AdapterVh(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFileSelected(files.get(vh.getAdapterPosition()), vh.getAdapterPosition());
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVh holder, int position) {
        holder.tvSlNo.setText(String.valueOf(position+1));

        switch (files.get(position).getDocType()) {
            case APPLICANT_ID_PROOF:
                holder.tvName.setText("Applicant Id Proof");
                break;
            case APPLICATION:
                holder.tvName.setText("Application");
                break;
            case MEDICAL_DOCUMENT:
                holder.tvName.setText("Medical Document");
                break;
            case VEHICLE_RC:
                holder.tvName.setText("Vehicle Rc");
                break;
            case OTHER:
                holder.tvName.setText("Other document");
                break;
        }



        if(!modifiable) holder.ivDelete.setVisibility(View.GONE);
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFileDeleteClicked(files.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class AdapterVh extends RecyclerView.ViewHolder {

        TextView tvSlNo;
        TextView tvName;
        ImageView ivDelete;

        public AdapterVh(@NonNull View view) {
            super(view);

            tvSlNo = view.findViewById(R.id.tv_slno);
            tvName = view.findViewById(R.id.tv_file_name);
            ivDelete = view.findViewById(R.id.iv_delete);
        }
    }
}
