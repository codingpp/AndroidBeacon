package cn.panzi.receiver.app

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * Application
 * @author sunpan
 * @date 2018/11/21
 */
class BeaconApplication : Application() {

    private var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        refWatcher = setupLeakCanary()
    }

    private fun setupLeakCanary(): RefWatcher {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED
        }
        return LeakCanary.install(this)
    }

    companion object {

        fun getRefWatcher(context: Context): RefWatcher? {
            val application = context.applicationContext as BeaconApplication
            return application.refWatcher
        }
    }

}