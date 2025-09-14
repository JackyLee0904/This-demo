package com.android.musicapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.musicapp.R;

public class BluetoothScanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_scan);
        getSupportFragmentManager().beginTransaction().replace(R.id.bluetooth_scan,new BluetoothScanFragment()).commit();
    }
}
