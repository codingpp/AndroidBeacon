package  cn.condingpp.beacon.ext

import android.app.Activity
import android.widget.Toast

/**
 *
 * @author codingpp
 * @date 2018/11/19
 */
fun Activity.showToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}