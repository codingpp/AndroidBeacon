package cn.condingpp.beacon.receive.imageloader.glide

import android.widget.ImageView
import cn.condingpp.beacon.receive.imageloader.ImageLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Glide加载实现
 * Created by SunPan on 2018/7/24.
 */
class GlideImageLoader : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    override fun loadImage(localImage: Int, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(localImage)
            .into(imageView)
    }

    override fun loadImage(url: String, imageView: ImageView, defaultImage: Int, errorImage: Int) {
        var options = RequestOptions()
        if (defaultImage != -1) {
            options = options.placeholder(defaultImage)
        }
        if (errorImage != -1) {
            options = options.error(errorImage)
        }
        Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }

    override fun loadCircleImage(url: String, imageView: ImageView) {
        val options = RequestOptions().circleCrop()
        Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }

    override fun loadCircleImage(localImage: Int, imageView: ImageView) {
        val options = RequestOptions().circleCrop()
        Glide.with(imageView.context)
            .load(localImage)
            .apply(options)
            .into(imageView)
    }

    override fun loadCircleImage(
        url: String,
        imageView: ImageView,
        defaultImage: Int,
        errorImage: Int
    ) {
        var options = RequestOptions().circleCrop()
        if (defaultImage != -1) {
            options = options.placeholder(defaultImage)
        }
        if (errorImage != -1) {
            options = options.error(errorImage)
        }
        Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }

    override fun loadRoundedImage(url: String, imageView: ImageView, radius: Int) {
        val options = RequestOptions().transform(GlideRoundedTransform(radius))
        Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }

    override fun loadRoundedImage(localImage: Int, imageView: ImageView, radius: Int) {
        val options = RequestOptions().transform(GlideRoundedTransform(radius))
        Glide.with(imageView.context)
            .load(localImage)
            .apply(options)
            .into(imageView)
    }

    override fun loadRoundedImage(
        url: String,
        imageView: ImageView,
        radius: Int,
        defaultImage: Int,
        errorImage: Int
    ) {
        var options = RequestOptions().transform(GlideRoundedTransform(radius))
        if (defaultImage != -1) {
            options = options.placeholder(defaultImage)
        }
        if (errorImage != -1) {
            options = options.error(errorImage)
        }
        Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
    }

}