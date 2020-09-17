package com.mibtech.huparidiary.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mibtech.huparidiary.R;

public class UploadWaitDialog extends Dialog {
    public UploadWaitDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_wait_dialog);
    }

}

