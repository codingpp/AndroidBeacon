package cn.condingpp.receiver.permission

import android.app.Activity
import com.tbruyelle.rxpermissions.RxPermissions
import java.util.*

/**
 * 权限请求
 * @author codingpp
 * @date 2018/9/26
 */
class RxPermissionRequest : RequestPermission {

    override fun request(
        activity: Activity,
        requestCallback: RequestCallback,
        vararg permission: String
    ) {
        val rxPermissions = RxPermissions(activity)
        if (permission.isEmpty()) {
            return
        }

        val needRequest = ArrayList<String>()
        for (per in permission) {
            if (!rxPermissions.isGranted(per)) {
                needRequest.add(per)
            }
        }
        needRequest.toTypedArray()
        if (needRequest.size == 0) {
            requestCallback.onRequestPermissionSuccess()
        } else {
            rxPermissions
                .request(*needRequest.toTypedArray())
                .subscribe { granted ->
                    if (granted) {
                        requestCallback.onRequestPermissionSuccess()
                    } else {
                        requestCallback.onRequestPermissionFailure()
                    }
                }
        }
    }


}