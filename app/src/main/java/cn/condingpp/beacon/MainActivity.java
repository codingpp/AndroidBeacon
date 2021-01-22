package cn.condingpp.beacon;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.codingpp.beacon.databinding.ActivityMainBinding;
import cn.condingpp.beacon.broadcast.BroadcastActivity;
import cn.condingpp.beacon.receive.ReceiveActivity;
import cn.condingpp.beacon.receive.permission.RequestCallback;
import cn.condingpp.beacon.receive.permission.RxPermissionRequest;

/**
 * 主页
 *
 * @author codingpp
 * @date 1/22/21
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        requestPermission();
    }

    /**
     * 初始化view
     */
    private void initView() {
        binding.btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastActivity.Companion.jump(MainActivity.this);
            }
        });
        binding.btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceiveActivity.Companion.jump(MainActivity.this);
            }
        });
    }

    private void requestPermission() {
        RxPermissionRequest request = new RxPermissionRequest();
        request.request(this, new RequestCallback() {
            @Override
            public void onRequestPermissionSuccess() {

            }

            @Override
            public void onRequestPermissionFailure() {

            }
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

}