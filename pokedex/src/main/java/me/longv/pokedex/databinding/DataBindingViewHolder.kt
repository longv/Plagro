package me.longv.pokedex.databinding

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder(private val viewBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(viewBinding.root), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun onAppear() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun onDisappear() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    fun bind(bindingEntry: DataBindingEntry) {
        viewBinding.apply {
            bindingEntry.bindings.forEach { setVariable(it.key, it.value) }
            lifecycleOwner = this@DataBindingViewHolder
        }

    }

    override fun getLifecycle(): Lifecycle  = lifecycleRegistry
}