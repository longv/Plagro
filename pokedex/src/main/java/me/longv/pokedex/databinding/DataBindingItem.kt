package me.longv.pokedex.databinding

import androidx.annotation.LayoutRes

data class DataBindingItem(@LayoutRes val layoutId: Int, val bindings: Map<Int, Any?>)