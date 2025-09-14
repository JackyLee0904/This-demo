package com.android.musicapp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.musicapp.R;
import com.android.musicapp.adapter.DeviceAdapter;
import com.android.musicapp.databinding.FragmentBluetoothScanBinding;
import com.android.musicapp.viewmodel.BluetoothViewModel;


public class BluetoothScanFragment extends Fragment {
    private FragmentBluetoothScanBinding binding;
    private BluetoothViewModel bluetoothViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_bluetooth_scan,
                container,
                false);
       bluetoothViewModel = new BluetoothViewModel();
       binding.setViewModel(bluetoothViewModel);
       binding.setLifecycleOwner(getViewLifecycleOwner());

       //初始化RecycleView
        DeviceAdapter adapter = new DeviceAdapter(bluetoothViewModel.devices, bluetoothViewModel);
        binding.deviceList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.deviceList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_SCAN})
    private  final ActivityResultLauncher<String []>  requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissons->{
       boolean allGranted = true;
       for (Boolean granted:permissons.values()){
           if(!granted){
               allGranted = false;
               break;
           }
       }
       if(allGranted){
           bluetoothViewModel.startScan();
       }else {
           Log.d("","permission is not granted");
       }
    });
    private void requestBluetoothPermissions() {
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions = new String[]{
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
        } else {
            permissions = new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
        }
        requestPermissionLauncher.launch(permissions);
    }
}
