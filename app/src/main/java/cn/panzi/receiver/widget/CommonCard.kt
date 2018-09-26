package cn.panzi.receiver.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import cn.panzi.receiver.R
import cn.panzi.receiver.imageloader.CommonLoader
import kotlinx.android.synthetic.main.common_card.view.*

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
        if (!TextUtils.isEmpty(cardTitle)) {
            tv_card_title.text = cardTitle
        }
        if (!TextUtils.isEmpty(cardSubscribe)) {
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
        if (!TextUtils.isEmpty(cardTitle)) {
            tv_card_title.text = cardTitle
        }
    }

    /**
     * 设置副标题
     */
    fun setCardSubscribeText(subscribe: String) {
        if (!TextUtils.isEmpty(subscribe)) {
            tv_card_subscribe.text = subscribe
        }
    }

    /**
     * 加载网络图片
     */
    fun setCardImage(imageUrl: String) {
        CommonLoader.loadImage(imageUrl, img_card_image)
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