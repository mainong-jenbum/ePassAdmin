package com.jenbumapps.e_passbordumsa_admin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jenbumapps.core.model.EPassTerm;
import com.jenbumapps.e_passbordumsa_admin.R;

import java.util.List;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.AdapterVh> {

    private List<EPassTerm> terms;

    public TermsAdapter(List<EPassTerm> terms) {
        this.terms = terms;
    }

    @NonNull
    @Override
    public AdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_terms_condition_item, parent, false);
        return new AdapterVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVh holder, int position) {
        holder.tvSlNo.setText(String.valueOf(position+1));
        holder.tvDescription.setText(terms.get(position).getTerm().getCondition());
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    class AdapterVh extends RecyclerView.ViewHolder {

        TextView tvSlNo;
        TextView tvDescription;

        public AdapterVh(@NonNull View view) {
            super(view);

            tvSlNo = view.findViewById(R.id.tv_slno);
            tvDescription = view.findViewById(R.id.tv_description);
        }
    }
}
