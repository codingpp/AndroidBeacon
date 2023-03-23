package cn.condingpp.beacon.receive.imageloader.glide

import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * Created by SunPan on 2018/7/24.
 */
class GlideRoundedTransform @JvmOverloads constructor(dp: Int = 4) : BitmapTransformation() {

    private var radius = 0f

    init {
        radius = Resources.getSystem().displayMetrics.density * dp
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        return roundCrop(pool, toTransform)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap?): Bitmap? {
        source?.let {
            var result: Bitmap? = pool.get(it.width, it.height, Bitmap.Config.ARGB_8888)
            if (result == null) {
                result = Bitmap.createBitmap(it.width, it.height, Bitmap.Config.ARGB_8888)
            }
            val canvas = Canvas(result!!)
            val paint = Paint()
            paint.shader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.isAntiAlias = true
            val rectF = RectF(0f, 0f, it.width.toFloat(), it.height.toFloat())
            canvas.drawRoundRect(rectF, radius, radius, paint)
            return result
        }
        return null
    }
}