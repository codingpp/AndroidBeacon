package cn.condingpp.beacon.broadcast

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.codingpp.beacon.databinding.ActivityAdvertiseBinding
import cn.condingpp.beacon.util.FormatUtil
import cn.condingpp.beacon.ext.showToast

/**
 * 广播页面
 * @author condingpp
 * @date 2018/9/20
 */
class AdvertiseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvertiseBinding

    private lateinit var mBluetoothAdapter: BluetoothAdapter
    private lateinit var mBluetoothLeAdvertiser: BluetoothLeAdvertiser

    private var mUuid = ""
    private var mMajor = 0
    private var mMinor = 0

    companion object {
        /**
         * 跳转
         */
        fun jumpFrom(context: Context, uuid: String, major: Int, minor: Int) {
            val intent = Intent(context, AdvertiseActivity::class.java)
            val bundle = Bundle()
            bundle.putString(BeaconConstant.UUID, uuid)
            bundle.putInt(BeaconConstant.MAJOR, major)
            bundle.putInt(BeaconConstant.MINOR, minor)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvertiseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initParams()
    }

    private fun initParams() {
        intent.extras?.let {
            val bundle = it
            mUuid = bundle.getString(BeaconConstant.UUID, "")
            mMajor = bundle.getInt(BeaconConstant.MAJOR, 0)
            mMinor = bundle.getInt(BeaconConstant.MINOR, 0)
        }

        val manager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = manager.adapter

        mBluetoothLeAdvertiser = mBluetoothAdapter.bluetoothLeAdvertiser

        if (checkParams()) {
            startAdvertise()
        }
    }

    private fun startAdvertise() {
        val advertiseSettings = createAdvertiseSettings(0)
        val advertiseData = createAdvertiseData(-59)
        mBluetoothLeAdvertiser.startAdvertising(advertiseSettings, advertiseData, advertiseCallback)
    }

    @Suppress("SameParameterValue")
    private fun createAdvertiseSettings(timeoutMillis: Int): AdvertiseSettings {
        val builder = AdvertiseSettings.Builder()
        builder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
        builder.setTimeout(timeoutMillis)
        builder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
        return builder.build()
    }

    @Suppress("SameParameterValue")
    private fun createAdvertiseData(txPower: Int): AdvertiseData {
        val builder = AdvertiseData.Builder()
        val beaconType = "0215"
        val uuid = mUuid.replace("-", "")
        val major = FormatUtil.formatStringLength(4, Integer.toHexString(mMajor), '0')
        val minor = FormatUtil.formatStringLength(4, Integer.toHexString(mMinor), '0')
        val measuredPower = FormatUtil.formatStringLength(2, Integer.toHexString(txPower), '0')

        val dataStr = beaconType + uuid + major + minor + measuredPower

        val data = FormatUtil.hexStringToByteArray(dataStr)

        builder.addManufacturerData(0x004C, data)

        return builder.build()
    }

    private var advertiseCallback = object : AdvertiseCallback() {

        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            showToast("开启成功")
            binding.successView.visibility = View.VISIBLE
            binding.failView.visibility = View.GONE
        }

        override fun onStartFailure(errorCode: Int) {
            showToast("开启失败$errorCode")
            binding.successView.visibility = View.GONE
            binding.failView.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBluetoothLeAdvertiser.stopAdvertising(advertiseCallback)
    }

    /**
     * 检查初始化参数
     */
    private fun checkParams(): Boolean {
        if (mUuid.isBlank()) {
            showToast("UUID不能为空")
            return false
        }
        return true
    }

}