package com.geek.rick_and_morty_api.ui.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.databinding.ItemEpisodeRvBinding
import com.geek.rick_and_morty_api.domain.model.Episodes
import javax.inject.Inject

class EpisodeAdapter @Inject constructor() :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    private var list: List<Episodes> = ArrayList()
    private var listener: ((Int) -> Unit)? = null

    fun update(list: List<Episodes>, listener: (Int) -> Unit) {
        this.list = list
        this.listener = listener
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ui = ItemEpisodeRvBinding.bind(itemView)

        fun onBind(data: Episodes) {
            ui.nameTextRv.text = data.name
            ui.dateTextRv.text = data.air_date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            list[position].id?.let { it1 -> listener?.invoke(it1) }
        }
    }

    override fun getItemCount(): Int = list.size
}