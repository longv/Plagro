package me.longv.pokedex.databinding

import androidx.recyclerview.widget.RecyclerView

data class RecyclerViewModel(val layoutManager: RecyclerView.LayoutManager,
                             val itemDecoration: RecyclerView.ItemDecoration? = null,
                             val bindingEntries: List<DataBindingEntry> = emptyList())