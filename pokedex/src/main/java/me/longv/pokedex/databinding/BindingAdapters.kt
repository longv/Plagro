package me.longv.pokedex.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["model"])
fun setupRecyclerView(view: RecyclerView, model: RecyclerViewModel) {
    model.apply {
        view.adapter = DataBindingAdapter(bindingItems)
        view.layoutManager = layoutManager
        itemDecoration?.apply {
            view.addItemDecoration(this)
        }
    }
}