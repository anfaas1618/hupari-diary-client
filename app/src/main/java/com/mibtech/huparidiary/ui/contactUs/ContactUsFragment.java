package com.mibtech.huparidiary.ui.contactUs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.mibtech.huparidiary.R;

public class ContactUsFragment extends Fragment{
    Button call,callDev;
    static int REQUEST_PHONE_CALL=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        call=root.findViewById(R.id.callBtn);
        callDev = root.findViewById(R.id.devBtn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9921484898"));// Initiates the Intent
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), "[call error ] "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9921484898"));// Initiates the Intent
                    startActivity(intent);
                }
            }
        });
        callDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "7974769929"));// Initiates the Intent
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), "[call error ] "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "7974769929"));// Initiates the Intent
                    startActivity(intent);
                }
            }
        });
        return root;
    }


}