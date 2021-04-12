package com.example.liststraining

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.liststraining.data.models.Champion

class ChampsAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<ChampionsViewHolder>() {
    private var champions = listOf<Champion>()

    override fun getItemViewType(position: Int): Int{
        return when  (champions.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_CHAMPIONS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionsViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_champs_empty,parent,false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_champs_data,parent,false)
            )
        }
    }

    override fun onBindViewHolder(holder: ChampionsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(champions[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(champions[position])
                }
            }
            is EmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = champions.size

    fun bindChampions(newActors: List<Champion>) {
        champions = newActors
        notifyDataSetChanged()
    }



}

private class EmptyViewHolder(itemView: View) : ChampionsViewHolder(itemView)
private class DataViewHolder(itemView: View) : ChampionsViewHolder(itemView) {

    private val icon: ImageView = itemView.findViewById(R.id.iv_champion_avatar)
    private val name: TextView = itemView.findViewById(R.id.tv_champion_name)
    private val isRange: TextView = itemView.findViewById(R.id.tv_is_ranged)

    fun onBind(champion: Champion) {
        Glide.with(context)
            .load(champion.icon)
            .apply(imageOption)
            .into(icon)

        name.text = champion.name

        isRange.text = context.getString(
            R.string.fragment_actors_avatar_oscar_state_text,
            champion.isRange.toString()
        )
    }

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
            .circleCrop()
    }
}

abstract class ChampionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_CHAMPIONS = 1

interface OnRecyclerItemClicked {
    fun onClick(champion: Champion)
}