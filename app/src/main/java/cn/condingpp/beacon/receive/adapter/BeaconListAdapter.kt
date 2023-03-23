package cn.condingpp.beacon.receive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.codingpp.beacon.R
import cn.codingpp.beacon.databinding.ItemBeaconBinding
import cn.condingpp.beacon.widget.CommonCard
import org.altbeacon.beacon.Beacon

/**
 * Beacon设备列表适配器
 * @author SunPan
 * @date 2018/9/25
 */
class BeaconListAdapter(
    private var context: Context,
    private var beaconList: List<Beacon>
) : RecyclerView.Adapter<BeaconListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemBeaconBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return beaconList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(beaconList: List<Beacon>) {
        this.beaconList = beaconList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {
        val name = beaconList[p1].id1.toString()
        val distance = beaconList[p1].distance.toString()
        holder.commonCard.setCardTitleText(name)
        holder.commonCard.setCardSubscribeText(distance)
        holder.commonCard.setCardImageRes(R.mipmap.ic_launcher)
    }

    inner class MyHolder(binding: ItemBeaconBinding) : RecyclerView.ViewHolder(binding.root) {
        var commonCard: CommonCard = binding.commonCard
    }

}