package me.longv.pokedex

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.longv.pokedex.databinding.DataBindingEntry
import me.longv.pokedex.databinding.RecyclerViewModel
import me.sargunvohra.lib.pokekotlin.model.Item
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.Version

object MainUiUtils {
    fun createMainListModel(context: Context, mainViewModel: MainViewModel): RecyclerViewModel =
        RecyclerViewModel(
            layoutManager = LinearLayoutManager(context),
            bindingEntries = listOf(
                DataBindingEntry(R.layout.main_item_list, mapOf(
                    BR.title to mainViewModel.gameVersionGroup.title,
                    BR.listModel to createGameVersionListModel(context, mainViewModel.gameVersionGroup)
                )),
                DataBindingEntry(R.layout.main_item_list, mapOf(
                    BR.title to mainViewModel.pokemonGroup.title,
                    BR.listModel to createPokemonListModel(context, mainViewModel.pokemonGroup)
                )),
                DataBindingEntry(R.layout.main_item_list, mapOf(
                    BR.title to mainViewModel.itemGroup.title,
                    BR.listModel to createItemListModel(context, mainViewModel.itemGroup)
                ))
            )
        )

    private fun createGameVersionListModel(context: Context, gameVersionGroup: PokeGroup<List<Version>>): LiveData<RecyclerViewModel> =
        Transformations.map(gameVersionGroup.content) { versions ->
            RecyclerViewModel(
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                bindingEntries = versions.map { DataBindingEntry(R.layout.main_item_game_version, mapOf(BR.data to it)) }
            )
        }

    private fun createPokemonListModel(context: Context, pokemonGroup: PokeGroup<List<Pokemon>>): LiveData<RecyclerViewModel> =
        Transformations.map(pokemonGroup.content) { pokemons ->
            RecyclerViewModel(
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                bindingEntries = pokemons.map { DataBindingEntry(R.layout.main_item_pokemon, mapOf(BR.data to it)) }
            )
        }

    private fun createItemListModel(context: Context, itemGroup: PokeGroup<List<Item>>): LiveData<RecyclerViewModel> =
        Transformations.map(itemGroup.content) { items ->
            RecyclerViewModel(
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
                bindingEntries = items.map { DataBindingEntry(R.layout.main_item_item, mapOf(BR.data to it)) }
            )
        }
}