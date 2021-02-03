package cn.condingpp.beacon;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;

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

    /**
     * 请求权限
     */
    private void requestPermission() {
        PermissionX.init(this)
                .permissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .onExplainRequestReason(new ExplainReasonCallback() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList) {
                        scope.showRequestReasonDialog(deniedList, "即将重新申请的权限是程序必须依赖的权限", "好的", "取消");
                    }
                }).request(new RequestCallback() {
            @Override
            public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                if (!allGranted) {
                    Toast.makeText(MainActivity.this, "权限已拒绝", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
