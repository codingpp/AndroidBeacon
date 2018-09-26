package cn.panzi.receiver.imageloader

import android.widget.ImageView
import cn.panzi.receiver.imageloader.glide.GlideImageLoader

/**
 * Created by SunPan on 2018/7/24.
 */
@Suppress("unused")
class CommonLoader private constructor() {

    private val imageLoader: ImageLoader

    init {
        imageLoader = GlideImageLoader()
    }

    private object Holder {
        val INSTANCE = CommonLoader()
    }

    companion object {

        private val instance: CommonLoader
            get() = Holder.INSTANCE

        fun loadImage(url: String, imageView: ImageView) {
            instance.imageLoader.loadImage(url, imageView)
        }

        fun loadImage(localImage: Int, imageView: ImageView) {
            instance.imageLoader.loadImage(localImage, imageView)

        }

        fun loadImage(url: String, imageView: ImageView, defaultImage: Int, errorImage: Int) {
            instance.imageLoader.loadImage(url, imageView, defaultImage, errorImage)
        }

        fun loadCircleImage(url: String, imageView: ImageView) {
            instance.imageLoader.loadCircleImage(url, imageView)
        }

        fun loadCircleImage(localImage: Int, imageView: ImageView) {
            instance.imageLoader.loadCircleImage(localImage, imageView)
        }

        fun loadCircleImage(url: String, imageView: ImageView, defaultImage: Int, errorImage: Int) {
            instance.imageLoader.loadCircleImage(url, imageView, defaultImage, errorImage)
        }

        fun loadRoundedImage(url: String, imageView: ImageView, radios: Int) {
            instance.imageLoader.loadRoundedImage(url, imageView, radios)
        }

        fun loadRoundedImage(localImage: Int, imageView: ImageView, radios: Int) {
            instance.imageLoader.loadRoundedImage(localImage, imageView, radios)
        }

        fun loadRoundedImage(
            url: String,
            imageView: ImageView,
            radios: Int,
            defaultImage: Int,
            errorImage: Int
        ) {
            instance.imageLoader.loadRoundedImage(url, imageView, radios, defaultImage, errorImage)
        }

    }

}