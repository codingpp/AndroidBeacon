package cn.condingpp.beacon.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import cn.codingpp.beacon.R
import cn.codingpp.beacon.databinding.CommonCardBinding
import com.bumptech.glide.Glide

/**
 * CardView
 * Created by SunPan on 2018/7/20.
 */
@Suppress("unused")
class CommonCard constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private var cardTitle: String? = ""
    private var cardImage: Int = -1
    private var cardSubscribe: String? = ""
    private lateinit var commonCardBinding: CommonCardBinding

    init {
        initView()
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.CommonCard
        )
        cardTitle = typedArray.getString(R.styleable.CommonCard_card_title)
        cardImage = typedArray.getResourceId(R.styleable.CommonCard_card_image, -1)
        cardSubscribe = typedArray.getString(R.styleable.CommonCard_card_subscribe)
        typedArray.recycle()
    }

    private fun initView() {
        commonCardBinding = CommonCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!cardTitle.isNullOrBlank()) {
            commonCardBinding.tvCardTitle.text = cardTitle
        }
        if (!cardSubscribe.isNullOrBlank()) {
            commonCardBinding.tvCardSubscribe.text = cardSubscribe
        }
        if (-1 != cardImage) {
            commonCardBinding.imgCardImage.setImageResource(cardImage)
        }
    }

    /**
     * 设置标题
     */
    fun setCardTitleText(cardTitle: String) {
        if (cardTitle.isNotBlank()) {
            commonCardBinding.tvCardTitle.text = cardTitle
        }
    }

    /**
     * 设置副标题
     */
    fun setCardSubscribeText(subscribe: String) {
        if (subscribe.isNotBlank()) {
            commonCardBinding.tvCardSubscribe.text = subscribe
        }
    }

    /**
     * 加载网络图片
     */
    fun setCardImage(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(commonCardBinding.imgCardImage)
    }

    /**
     * 加载本地图片
     */

    fun setCardImageRes(@DrawableRes imageRes: Int) {
        if (-1 != imageRes) {
            commonCardBinding.imgCardImage.setImageResource(imageRes)
        }
    }

}