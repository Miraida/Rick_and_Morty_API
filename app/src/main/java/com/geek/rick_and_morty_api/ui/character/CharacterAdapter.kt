package com.geek.rick_and_morty_api.ui.character

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.rick_and_morty_api.R
import com.geek.rick_and_morty_api.databinding.ItemCharacterRvBinding
import com.geek.rick_and_morty_api.domain.model.Characters
import com.geek.rick_and_morty_api.extension.loadImage
import javax.inject.Inject

class CharacterAdapter @Inject constructor() :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var list: List<Characters> = ArrayList()
    private var listener: ((Int) -> Unit)? = null

    fun update(list: List<Characters>,listener:(Int) -> Unit) {
        this.list = list
        this.listener = listener
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ui = ItemCharacterRvBinding.bind(itemView)

        fun onBind(character: Characters) {
            Log.e("TAG", "onBind: $character" )
            character.image?.let { ui.imageRv.loadImage(it) }
            ui.nameTextRv.text = character.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character_rv, parent, false)
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