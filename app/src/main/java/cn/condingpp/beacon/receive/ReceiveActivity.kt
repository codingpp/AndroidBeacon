package cn.condingpp.beacon.receive

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.codingpp.beacon.databinding.ActivityReceiveBinding
import cn.condingpp.beacon.ext.showToast
import cn.condingpp.beacon.receive.adapter.BeaconListAdapter
import cn.condingpp.beacon.receive.permission.RequestCallback
import cn.condingpp.beacon.receive.permission.RxPermissionRequest
import org.altbeacon.beacon.*
import cn.codingpp.beacon.R


/**
 * 接收页面
 * @author codingpp
 */

class ReceiveActivity : AppCompatActivity(), BeaconConsumer {

    private lateinit var binding: ActivityReceiveBinding

    protected val TAG = "MonitoringActivity"
    private val BEACON_LAYOUT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"

    private val PERMISSION_REQUEST_COARSE_LOCATION: Int = 1001
    private lateinit var beaconList: ArrayList<Beacon>

    private lateinit var beaconManager: BeaconManager
    private lateinit var adapter: BeaconListAdapter

    companion object {
        /**
         * 跳转到接收页面
         * @param context ctx
         */
        fun jump(context: Context) {
            val intent = Intent(context, ReceiveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        requestPermission()
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
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(BEACON_LAYOUT))
        beaconManager.bind(this)
    }

    override fun onBeaconServiceConnect() {
        beaconManager.removeAllMonitorNotifiers()
        beaconManager.addRangeNotifier { beacons, p1 ->
            beacons?.let {
                Log.e(TAG, "onBeaconServiceConnect: " + beacons.size)
//                beaconList.clear()
//                beaconList.addAll(beacons)
                adapter.update(beacons.toMutableList())
            }
        }

        try {
            beaconManager.startRangingBeaconsInRegion(Region("", null, null, null))
        } catch (e: RemoteException) {
        }
    }

    /**
     * 权限请求
     */
    private fun requestPermission() {
        val requestPermission = RxPermissionRequest()
        requestPermission.request(this, object : RequestCallback {
            override fun onRequestPermissionSuccess() {
                initBeaconManager()
            }

            override fun onRequestPermissionFailure() {
                showToast(getString(R.string.no_location_permission))
            }

        }, android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    /**
     * 权限请求回调
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_COARSE_LOCATION -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initBeaconManager()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager.unbind(this)
    }
}
