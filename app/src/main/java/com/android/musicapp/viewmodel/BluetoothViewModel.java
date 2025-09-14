package com.android.musicapp.viewmodel;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresPermission;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import java.util.List;


import leakcanary.AppWatcher;

public class BluetoothViewModel {
    private final String TAG = "BluetoothViewModel";
 private BluetoothAdapter bluetoothAdapter;
 private BluetoothLeScanner bluetoothLeScanner;
 private Handler handler;
public ObservableList<BluetoothDeviceInfo>  devices = new ObservableArrayList<>();
     public BluetoothViewModel(){
    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (bluetoothAdapter != null){
        AppWatcher.INSTANCE.getObjectWatcher().watch(bluetoothAdapter,"BluetoothAdapter");
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
    }
     handler = new Handler(Looper.getMainLooper());
     }
     @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
     public  void  startScan(){
       if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
           Log.d(TAG,"BLuetooth is disabled");
           return;
       }
         devices.clear();
         bluetoothLeScanner.startScan(new ScanCallback() {
           @Override
           public void onBatchScanResults(List<ScanResult> results) {
               super.onBatchScanResults(results);
           }

           @Override
           public void onScanFailed(int errorCode) {
               super.onScanFailed(errorCode);
           }

           @Override
           public void onScanResult(int callbackType, ScanResult result) {
               super.onScanResult(callbackType, result);
           }
       });
       handler.postDelayed(()->{
           bluetoothLeScanner.stopScan(new ScanCallback() {
               @Override
               public void onScanFailed(int errorCode) {
                   super.onScanFailed(errorCode);
               }

               @Override
               public void onScanResult(int callbackType, ScanResult result) {
                   super.onScanResult(callbackType, result);
               }
           });
       },100000);
     }

}
