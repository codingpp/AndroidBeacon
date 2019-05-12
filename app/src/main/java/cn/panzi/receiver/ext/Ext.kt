package cn.panzi.receiver.ext

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import cn.panzi.receiver.imageloader.CommonLoader

/**
 * 扩展函数
 * @author sunpan
 * @date 2018/11/19
 */


/**
 * Activity显示Toast
 */
fun Activity.showToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

/**
 * ImageView使用Glide加载图片
 */
fun ImageView.loadImage(imageUrl: String) {
    CommonLoader.loadImage(imageUrl, this)
}