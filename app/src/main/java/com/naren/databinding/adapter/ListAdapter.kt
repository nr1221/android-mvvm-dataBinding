package com.naren.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.naren.databinding.R
import com.naren.databinding.data.DataModel
import com.naren.databinding.databinding.DogCardBinding

class ListAdapter(val list: ArrayList<DataModel>) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    fun updateList(newList: ArrayList<DataModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<DogCardBinding>(inflater, R.layout.dog_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.view.model = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val view: DogCardBinding) : RecyclerView.ViewHolder(view.root)


}