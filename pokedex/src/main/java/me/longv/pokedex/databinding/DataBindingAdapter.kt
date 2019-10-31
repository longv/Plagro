package me.longv.pokedex.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingAdapter(private val bindingItems: List<DataBindingItem> = emptyList())
    : RecyclerView.Adapter<DataBindingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bind(bindingItems[position])
    }

    override fun getItemCount(): Int = bindingItems.size

    override fun getItemViewType(position: Int): Int = bindingItems[position].layoutId
}