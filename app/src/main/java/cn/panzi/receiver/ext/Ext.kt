package cn.panzi.receiver.ext

import android.app.Activity
import android.widget.Toast

/**
 *
 * @author sunpan
 * @date 2018/11/19
 */
fun Activity.showToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}