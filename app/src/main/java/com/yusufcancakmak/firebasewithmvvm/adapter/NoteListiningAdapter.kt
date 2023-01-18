package com.yusufcancakmak.firebasewithmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufcancakmak.firebasewithmvvm.data.model.Note
import com.yusufcancakmak.firebasewithmvvm.databinding.ItemNoteLayoutBinding

class NoteListiningAdapter(
    val onItemClicked:(Int,Note)-> Unit,
    val onEditCliked:(Int, Note)-> Unit,
    val onDeleteCicked:(Int,Note)->Unit
):RecyclerView.Adapter<NoteListiningAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemNoteLayoutBinding):RecyclerView.ViewHolder(binding.root)
    private var list :MutableList<Note> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentlist=list[position]

        holder.binding.noteIdValue.setText(currentlist.id)
        holder.binding.textPeter.setText(currentlist.text)
        holder.binding.create.setOnClickListener { onEditCliked.invoke(holder.adapterPosition,currentlist) }
        holder.binding.delete.setOnClickListener { onDeleteCicked.invoke(holder.adapterPosition,currentlist) }
        holder.itemView.setOnClickListener { onItemClicked.invoke(holder.adapterPosition,currentlist) }


    }

    override fun getItemCount()=list.size

    fun updateList(list: MutableList<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }
}