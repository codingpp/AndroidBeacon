package cn.condingpp.beacon.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import cn.codingpp.beacon.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.common_card.view.*

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
        View.inflate(context, R.layout.common_card, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!cardTitle.isNullOrBlank()) {
            tv_card_title.text = cardTitle
        }
        if (!cardSubscribe.isNullOrBlank()) {
            tv_card_subscribe.text = cardSubscribe
        }
        if (-1 != cardImage) {
            img_card_image.setImageResource(cardImage)
        }
    }

    /**
     * 设置标题
     */
    fun setCardTitleText(cardTitle: String) {
        if (!cardTitle.isBlank()) {
            tv_card_title.text = cardTitle
        }
    }

    /**
     * 设置副标题
     */
    fun setCardSubscribeText(subscribe: String) {
        if (!subscribe.isBlank()) {
            tv_card_subscribe.text = subscribe
        }
    }

    /**
     * 加载网络图片
     */
    fun setCardImage(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(img_card_image)
    }

    /**
     * 加载本地图片
     */

    fun setCardImageRes(@DrawableRes imageRes: Int) {
        if (-1 != imageRes) {
            img_card_image.setImageResource(imageRes)
        }
    }

}