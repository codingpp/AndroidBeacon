package cn.panzi.receiver.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.panzi.receiver.R
import cn.panzi.receiver.widget.CommonCard
import org.altbeacon.beacon.Beacon

/**
 *
 * @author sunpan
 * @date 2018/9/25
 */
class BeaconListAdapter(
    var context: Context,
    var beaconList: List<Beacon>
) : RecyclerView.Adapter<BeaconListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_beacon, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return beaconList.size
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {
        val name = beaconList[p1].id1.toString()
        val distance = beaconList[p1].distance.toString()
        holder.commonCard.setCardTitleText(name)
        holder.commonCard.setCardSubscribeText(distance)
        holder.commonCard.setCardImageRes(R.mipmap.ic_launcher)
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commonCard: CommonCard = itemView.findViewById(R.id.common_card)
    }

}