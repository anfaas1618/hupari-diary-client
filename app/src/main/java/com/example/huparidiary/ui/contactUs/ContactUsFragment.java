package com.example.huparidiary.ui.contactUs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.huparidiary.R;

public class ContactUsFragment extends AppCompatActivity{
    Button call,callDev;
    static int REQUEST_PHONE_CALL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contact_us);
        call=findViewById(R.id.callBtn);
        callDev = findViewById(R.id.devBtn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9921484898"));// Initiates the Intent
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(ContactUsFragment.this, "[call error ] "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if (ContextCompat.checkSelfPermission(ContactUsFragment.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactUsFragment.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
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
                    Toast.makeText(ContactUsFragment.this, "[call error ] "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if (ContextCompat.checkSelfPermission(ContactUsFragment.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactUsFragment.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "7974769929"));// Initiates the Intent
                    startActivity(intent);
                }
            }
        });

    }
}