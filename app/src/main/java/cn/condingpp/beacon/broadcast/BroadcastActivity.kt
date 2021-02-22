package cn.condingpp.beacon.broadcast

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.codingpp.beacon.databinding.ActivityBroadcastBinding
import java.util.*
import java.util.regex.Pattern

/**
 * 主页
 * @author codingpp
 * @date 2018/9/20
 */
class BroadcastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastBinding
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    companion object {
        /**
         * 跳转到发送页面
         */
        fun jump(context: Context) {
            val intent = Intent(context, BroadcastActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        val bluetoothManager =
            getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = bluetoothManager.adapter

        binding.tvSend.setOnClickListener {
            startAction()
        }
    }

    /**
     * 检查UUID,Major,Minor是否合法
     */
    private fun startAction() {
        if (!mBluetoothAdapter.isEnabled) {
            Toast.makeText(this, "请开启蓝牙", Toast.LENGTH_SHORT).show()
            return
        }

        val uuid = binding.etUuid.text.toString().toUpperCase(Locale.getDefault())
        val isValidUUID: Boolean = isValidUUID(uuid)
        if (!isValidUUID) {
            Toast.makeText(this, "UUID格式错误", Toast.LENGTH_SHORT).show()
            return
        }

        var major = -1
        if (binding.etMajor.text.toString().isNotBlank()) {
            major = Integer.parseInt(binding.etMajor.text.toString())
        }
        if (major < 0 || major > 65535) {
            Toast.makeText(this, "Major值为0-65535", Toast.LENGTH_SHORT).show()
            return
        }

        var minor = -1
        if (binding.etMinor.text.toString().isNotBlank()) {
            minor = Integer.parseInt(binding.etMinor.text.toString())
        }
        if (minor < 0 || minor > 65535) {
            Toast.makeText(this, "Minor值为0-65535", Toast.LENGTH_SHORT).show()
            return
        }

        AdvertiseActivity.jumpFrom(this, uuid, major, minor)
    }

    /**
     * 校验UUID是否合法
     */
    private fun isValidUUID(uuid: String): Boolean {
        val regEx =
            "^[a-fA-F0-9]{8}[-][a-fA-F0-9]{4}[-][a-fA-F0-9]{4}[-][a-fA-F0-9]{4}[-][a-fA-F0-9]{12}$"
        val pattern = Pattern.compile(regEx)
        val matcher = pattern.matcher(uuid)
        return matcher.matches()
    }
}
