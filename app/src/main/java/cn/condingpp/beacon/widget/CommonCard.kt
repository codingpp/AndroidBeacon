package cn.condingpp.beacon.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import cn.codingpp.beacon.R
import com.bumptech.glide.Glide

/**
 * CardView
 * Created by codingpp on 2018/7/20.
 */
@Suppress("unused")
class CommonCard constructor(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private var cardTitle: String? = ""
    private var cardImage: Int = -1
    private var cardSubscribe: String? = ""
    private lateinit var tvCardTitle: TextView
    private lateinit var imgCardImage: ImageView
    private lateinit var tvCardSubscribe: TextView

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
        val view = View.inflate(context, R.layout.common_card, this)
        tvCardTitle = view.findViewById(R.id.tv_card_title)
        tvCardSubscribe = view.findViewById(R.id.tv_card_subscribe)
        imgCardImage = view.findViewById(R.id.img_card_image)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!cardTitle.isNullOrBlank()) {
            tvCardTitle.text = cardTitle
        }
        if (!cardSubscribe.isNullOrBlank()) {
            tvCardSubscribe.text = cardSubscribe
        }
        if (-1 != cardImage) {
            imgCardImage.setImageResource(cardImage)
        }
    }

    /**
     * 设置标题
     */
    fun setCardTitleText(cardTitle: String) {
        if (cardTitle.isNotBlank()) {
            tvCardTitle.text = cardTitle
        }
    }

    /**
     * 设置副标题
     */
    fun setCardSubscribeText(subscribe: String) {
        if (subscribe.isNotBlank()) {
            tvCardSubscribe.text = subscribe
        }
    }

    /**
     * 加载网络图片
     */
    fun setCardImage(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(imgCardImage)
    }

    /**
     * 加载本地图片
     */

    fun setCardImageRes(@DrawableRes imageRes: Int) {
        if (-1 != imageRes) {
            imgCardImage.setImageResource(imageRes)
        }
    }

}