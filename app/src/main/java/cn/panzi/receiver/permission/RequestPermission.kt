package cn.panzi.receiver.permission

import android.app.Activity

/**
 *
 * @author sunpan
 * @date 2018/9/26
 */
interface RequestPermission {

    /**
     *请求权限
     * @param activity activity
     * @param requestCallback 回调
     * @param permission 权限
     */
    fun request(activity: Activity, requestCallback: RequestCallback, vararg permission: String)

}