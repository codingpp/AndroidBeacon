package cn.condingpp.beacon.receive.permission

/**
 * 请求权限回调
 * @author codingpp
 * @date 2018/9/26
 */
interface RequestCallback {

    /**
     * 请求权限成功
     */
    fun onRequestPermissionSuccess()

    /**
     * 请求权限失败
     */
    fun onRequestPermissionFailure()

}