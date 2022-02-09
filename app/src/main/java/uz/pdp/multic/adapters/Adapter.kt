package uz.pdp.multic.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.pdp.multic.databinding.ItemMultBinding
import uz.pdp.multic.models.MainDataModel

class Adapter : RecyclerView.Adapter<Adapter.VH>() {

    private var list = mutableListOf<MainDataModel>()
    private lateinit var allClickListener: (itemData: MainDataModel) -> Unit

    inner class VH(var binding: ItemMultBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(itemData: MainDataModel) {
            binding.apply {
                Glide.with(binding.root.context).load(itemData.result.image).into(binding.img)

                name.text = itemData.result.name
                val status = itemData.result.status
                when (status) {
                    "Alive" -> {
                        card.setCardBackgroundColor(Color.parseColor("#00E676"))
                    }
                    "Dead" -> {
                        card.setCardBackgroundColor(Color.parseColor("#FF1744"))
                    }
                    else -> {
                        card.setCardBackgroundColor(Color.parseColor("#CACCCB"))
                    }
                }

                isAlive.text = "$status - ${itemData.result.species}"
                location.text = "${itemData.result.location.name}"
                episode.text = "${itemData.episodeClass.name}"

                bigCard.setOnClickListener {
                    allClickListener.invoke(itemData)
                }

            }
        }
    }

    fun clickIm(c: (MainDataModel) -> Unit) {
        allClickListener = c
    }

    fun addData(data: List<MainDataModel>) {
        val size = this.list.size
        this.list.addAll(data.toMutableList())
        notifyItemRangeChanged(size, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}