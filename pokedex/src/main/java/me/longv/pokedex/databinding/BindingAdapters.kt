package me.longv.pokedex.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["model"])
fun setupRecyclerView(view: RecyclerView, model: RecyclerViewModel?) {
    model?.apply {
        view.adapter = DataBindingAdapter(bindingEntries)
        view.layoutManager = layoutManager
        itemDecoration?.apply {
            view.addItemDecoration(this)
        }
    }
}

@BindingAdapter(value = ["urlSrc"])
fun setImageSrc(view: ImageView, url: String?) {
    url?.also {
        Picasso.get()
            .load(it)
            .fit()
            .into(view)
    }
}