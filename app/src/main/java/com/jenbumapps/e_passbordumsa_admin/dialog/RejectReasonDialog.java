package com.jenbumapps.e_passbordumsa_admin.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jenbumapps.e_passbordumsa_admin.R;

public class RejectReasonDialog extends Dialog {

    private DialogListener mListener;
    private String title;
    private String message;

    public RejectReasonDialog(@NonNull Context context) {
        super(context);
    }

    public RejectReasonDialog setTitle(String title) {
        this.title = title;

        return this;
    }

    public RejectReasonDialog setMessage(String message){
        this.message = message;

        return this;
    }

    public RejectReasonDialog setListener(DialogListener listener){
        this.mListener = listener;
        return this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_reject_reason);
        setCancelable(false);

        // initialize view
        TextView btnOk = findViewById(R.id.btn_ok);
        TextView btnCancel = findViewById(R.id.btn_cancel);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);
        EditText etReason = findViewById(R.id.et_reason);

        // set data
        tvTitle.setText(title);
        tvMessage.setText(message);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etReason.getText().toString().isEmpty()){
                    tvMessage.setText("Rejection reason is required.");
                    return;
                }

                mListener.onApprove(etReason.getText().toString());
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDecline();
                dismiss();
            }
        });

        Window window = getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public interface DialogListener{
        void onApprove(String reason);
        void onDecline();
    }
}
