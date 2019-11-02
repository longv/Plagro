package me.longv.pokedex

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.Item
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import me.sargunvohra.lib.pokekotlin.model.Version

class MainViewModel: ViewModel() {
    val gameVersionGroup: PokeGroup<List<Version>> by lazy {
        PokeGroup(R.string.main_version_group_title, Transformations.map(gameVersions) {
            it
        })
    }

    val pokemonGroup: PokeGroup<List<Pokemon>> by lazy {
        PokeGroup(R.string.main_pokemon_title, Transformations.map(pokemons) {
            it
        })
    }

    val itemGroup: PokeGroup<List<Item>> by lazy {
        PokeGroup(R.string.main_item_title, Transformations.map(items) {
            it
        })
    }

    private val pokemons: MutableLiveData<List<Pokemon>> = MutableLiveData()

    private val gameVersions: MutableLiveData<List<Version>> = MutableLiveData()

    private val items: MutableLiveData<List<Item>> = MutableLiveData()

    private val pokeApi = PokeApiClient()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            getGameVersions().apply {
                gameVersions.postValue(this) }
            getPokemons().apply { pokemons.postValue(this) }
            getItems().apply { items.postValue(this) }
        }
    }

    private fun getGameVersions(limit: Int = 10): List<Version> {
        val offset = gameVersions.value?.size ?: 0
        return pokeApi.getVersionList(offset, limit).results.map { pokeApi.getVersion(it.id) }
    }

    private fun getPokemons(limit: Int = 10): List<Pokemon> {
        val offset = pokemons.value?.size ?: 0
        return pokeApi.getPokemonList(offset, limit).results.map { pokeApi.getPokemon(it.id) }
    }

    private fun getItems(limit: Int = 10): List<Item> {
        val offset = items.value?.size ?: 0
        return pokeApi.getItemList(offset, limit).results.map { pokeApi.getItem(it.id) }
    }
}

data class PokeGroup<T>(@StringRes val title: Int, val content: LiveData<T>)