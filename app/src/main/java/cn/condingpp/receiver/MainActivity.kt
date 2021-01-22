package cn.condingpp.receiver

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import cn.panzi.receiver.R
import cn.condingpp.receiver.adapter.BeaconListAdapter
import cn.condingpp.receiver.ext.showToast
import cn.condingpp.receiver.permission.RequestCallback
import cn.condingpp.receiver.permission.RxPermissionRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.altbeacon.beacon.*

/**
 * MainActivity
 * @author codingpp
 */

class MainActivity : AppCompatActivity(), BeaconConsumer {

    protected val TAG = "MonitoringActivity"
    private val BEACON_LAYOUT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"

    private val PERMISSION_REQUEST_COARSE_LOCATION: Int = 1001
    private lateinit var beaconList: ArrayList<Beacon>

    private lateinit var beaconManager: BeaconManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        requestPermission()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        beaconList = ArrayList()
        recycle_view.setHasFixedSize(true)
        val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recycle_view.layoutManager = linearLayoutManager
        recycle_view.adapter = BeaconListAdapter(beaconList)
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
                beaconList.clear()
                beaconList.addAll(beacons)
                recycle_view.adapter?.notifyDataSetChanged()
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
