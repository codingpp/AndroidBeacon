package cn.panzi.receiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.RemoteException
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import cn.panzi.receiver.adapter.BeaconListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.altbeacon.beacon.*

class MainActivity : AppCompatActivity(), BeaconConsumer {

    private val TAG = MainActivity::class.java.simpleName
    private val PERMISSION_REQUEST_COARSE_LOCATION: Int = 1001
    private val BEACON_LAYOUT: String = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"
    private lateinit var beaconList: ArrayList<Beacon>

    private lateinit var beaconManager: BeaconManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        requestPermission()
        initBeaconManager()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        beaconList = ArrayList()
        recycle_view.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycle_view.layoutManager = linearLayoutManager
        recycle_view.adapter = BeaconListAdapter(this, beaconList)
    }

    /**
     * 初始化BeaconManager
     */
    private fun initBeaconManager() {
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout(BEACON_LAYOUT))
        beaconManager.foregroundScanPeriod = 5000
        beaconManager.bind(this)
    }

    override fun onBeaconServiceConnect() {
        beaconManager.addRangeNotifier { beacons, _ ->
            if (beacons.isNotEmpty()) {
                Log.e(TAG, beacons.size.toString())
                beaconList.clear()
                beaconList.addAll(beacons)
                recycle_view.adapter!!.notifyDataSetChanged()
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
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_REQUEST_COARSE_LOCATION
                )
                return
            }
        }
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
            } else {
                //
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager.unbind(this)
    }
}
