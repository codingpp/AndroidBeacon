package cn.condingpp.beacon;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.permissionx.guolindev.PermissionX;

import java.util.ArrayList;
import java.util.List;

import cn.codingpp.beacon.databinding.ActivityMainBinding;
import cn.condingpp.beacon.broadcast.BroadcastActivity;
import cn.condingpp.beacon.receive.ReceiveActivity;

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
        binding.btnBroadcast.setOnClickListener(v -> BroadcastActivity.Companion.jump(MainActivity.this));
        binding.btnReceive.setOnClickListener(v -> ReceiveActivity.Companion.jump(MainActivity.this));
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_ADVERTISE);
            permissions.add(Manifest.permission.BLUETOOTH_SCAN);
        }
        PermissionX.init(this)
                .permissions(permissions)
                .onExplainRequestReason((scope, deniedList) -> scope.showRequestReasonDialog(deniedList, "即将重新申请的权限是程序必须依赖的权限", "好的", "取消")).request((allGranted, grantedList, deniedList) -> {
            if (!allGranted) {
                Toast.makeText(MainActivity.this, "权限已拒绝", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
