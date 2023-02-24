package cn.condingpp.beacon.receive

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.codingpp.beacon.R
import cn.codingpp.beacon.databinding.ActivityReceiveBinding
import cn.condingpp.beacon.receive.adapter.BeaconListAdapter
import org.altbeacon.beacon.*


/**
 * 接收页面
 * @author codingpp
 */

class ReceiveActivity : AppCompatActivity(), InternalBeaconConsumer {

    private lateinit var binding: ActivityReceiveBinding

    private val BEACON_LAYOUT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"

    private lateinit var beaconList: ArrayList<Beacon>

    private lateinit var beaconManager: BeaconManager
    private lateinit var adapter: BeaconListAdapter
    private lateinit var mBluetoothAdapter: BluetoothAdapter

    companion object {
        /**
         * 跳转到接收页面
         * @param context ctx
         */
        fun jump(context: Context) {
            val intent = Intent(context, ReceiveActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "ReceiveActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initBeaconManager()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        beaconList = ArrayList()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.recycleView.layoutManager = linearLayoutManager
        adapter = BeaconListAdapter(this, beaconList)
        binding.recycleView.adapter = adapter
    }

    /**
     * 初始化BeaconManager
     */
    private fun initBeaconManager() {
        val bluetoothManager =
            getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = bluetoothManager.adapter
        if (!mBluetoothAdapter.isEnabled) {
            Toast.makeText(this, R.string.open_bluetooth, Toast.LENGTH_SHORT).show()
            return
        }
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(BEACON_LAYOUT))
        beaconManager.bindInternal(this)
    }

    override fun onBeaconServiceConnect() {
        beaconManager.removeAllMonitorNotifiers()
        beaconManager.addRangeNotifier { beacons, _ ->
            beacons?.let {
                adapter.update(beacons.toMutableList())
            }
        }

        try {
            beaconManager.startRangingBeacons(Region("", null, null, null))
        } catch (e: RemoteException) {
            Log.e(TAG, "onBeaconServiceConnect e: " + e.message)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        beaconManager.unbindInternal(this)
    }
}
