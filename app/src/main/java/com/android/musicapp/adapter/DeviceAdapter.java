package com.android.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.android.musicapp.R;
import com.android.musicapp.databinding.ItemDeviceBinding;
import com.android.musicapp.viewmodel.BluetoothDeviceInfo;
import com.android.musicapp.viewmodel.BluetoothViewModel;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {
private final ObservableList<BluetoothDeviceInfo> devices;
private  final BluetoothViewModel viewModel;

public DeviceAdapter(ObservableList<BluetoothDeviceInfo> devices,BluetoothViewModel viewModel){
    this.devices = devices;
    this.viewModel = viewModel;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeviceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_device,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.binding.setDevice(devices.get(position));
    holder.binding.setViewModel(viewModel);
    holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemDeviceBinding binding;
        public ViewHolder(@NonNull ItemDeviceBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
