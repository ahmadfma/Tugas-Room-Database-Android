package com.example.roomdb

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.User.UserEntity
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

class MainAdapter(private val list: List<UserEntity>, private val listener: OnClickListener): RecyclerView.Adapter<MainAdapter.Holder>() {

    interface OnClickListener {
        fun onDeleteClick(note: UserEntity)
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: UserEntity, position: Int, listener: OnClickListener) {
            with(itemView) {
                setRandomColor(itemView, position)
                titleNote.text = note.title
                notetv.text = note.note
                deleteBtn.setOnClickListener {
                    listener.onDeleteClick(note)
                }
            }
        }
        private fun setRandomColor(itemView: View, position: Int) {
            val i = position % 4
            with(itemView) {
                when(i) {
                    0 -> colorLine.setImageResource(R.color.primary)
                    1 -> colorLine.setImageResource(R.color.orange)
                    2 -> colorLine.setImageResource(R.color.hijau)
                    3 -> colorLine.setImageResource(R.color.merah)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.Holder, position: Int) {
        holder.bind(list[position], position, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}