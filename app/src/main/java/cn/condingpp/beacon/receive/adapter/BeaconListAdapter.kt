package cn.condingpp.beacon.receive.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.codingpp.beacon.R
import cn.codingpp.beacon.databinding.ItemBeaconBinding
import org.altbeacon.beacon.Beacon

/**
 * Beacon设备列表适配器
 * @author codingpp
 * @date 2018/9/25
 */
class BeaconListAdapter(
    var context: Context,
    private var beaconList: List<Beacon>
) : RecyclerView.Adapter<BeaconListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemBeaconBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return beaconList.size
    }

    fun update(beaconList: List<Beacon>) {
        this.beaconList = beaconList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {
        val name = beaconList[p1].id1.toString()
        val distance = beaconList[p1].distance.toString()
        holder.binding.commonCard.setCardTitleText(name)
        holder.binding.commonCard.setCardSubscribeText(distance)
        holder.binding.commonCard.setCardImageRes(R.mipmap.ic_launcher)
    }

    inner class MyHolder(var binding: ItemBeaconBinding) : RecyclerView.ViewHolder(binding.root)

}