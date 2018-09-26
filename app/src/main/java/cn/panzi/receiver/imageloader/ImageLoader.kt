package cn.panzi.receiver.imageloader

import android.support.annotation.DrawableRes
import android.support.annotation.NonNull
import android.widget.ImageView

/**
 * 图片加载
 * Created by SunPan on 2018/7/24.
 */
interface ImageLoader {

    /**
     * 加载图片
     * @param url 链接
     * @param imageView image
     */
    fun loadImage(url: String, @NonNull imageView: ImageView)

    /**
     * 加载本地图片
     *
     * @param localImage 本地图片
     * @param imageView  image
     */
    fun loadImage(@DrawableRes localImage: Int, @NonNull imageView: ImageView)

    /**
     * 加载网络图片
     *
     * @param url          链接
     * @param imageView    imageview
     * @param defaultImage 默认图片
     * @param errorImage 错误图片
     */
    fun loadImage(url: String, @NonNull imageView: ImageView, @DrawableRes defaultImage: Int, @DrawableRes errorImage: Int)

    /**
     * 加载圆形图片
     *
     * @param url       链接
     * @param imageView image
     */
    fun loadCircleImage(url: String, @NonNull imageView: ImageView)

    /**
     * 加载本地圆角图片
     *
     * @param localImage 本地图片
     * @param imageView  image
     */
    fun loadCircleImage(@DrawableRes localImage: Int, @NonNull imageView: ImageView)

    /**
     * 加载圆形图片
     *
     * @param url          链接
     * @param imageView    image
     * @param defaultImage 默认图片
     * @param errorImage   错误图片
     */
    fun loadCircleImage(url: String, @NonNull imageView: ImageView, @DrawableRes defaultImage: Int, @DrawableRes errorImage: Int)

    /**
     * 加载圆角图片片
     *
     * @param url       链接
     * @param imageView image
     * @param radius    角度
     */
    fun loadRoundedImage(url: String, @NonNull imageView: ImageView, radius: Int)

    /**
     * 加载本地圆角图片
     *
     * @param localImage 本地图片
     * @param imageView  image
     * @param radius     角度
     */
    fun loadRoundedImage(@DrawableRes localImage: Int, @NonNull imageView: ImageView, radius: Int)

    /**
     * 加载圆角图片
     *
     * @param url          链接
     * @param imageView    image
     * @param radius     角度
     * @param defaultImage 默认图片
     * @param errorImage   错误图片
     */
    fun loadRoundedImage(
        url: String, @NonNull imageView: ImageView,
        radius: Int, @DrawableRes defaultImage: Int, @DrawableRes errorImage: Int
    )
}