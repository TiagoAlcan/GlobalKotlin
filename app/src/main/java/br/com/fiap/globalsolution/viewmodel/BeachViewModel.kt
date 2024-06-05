package br.com.fiap.globalsolution.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.globalsolution.model.Beach

class BeachViewModel : ViewModel() {

    private val beaches = mutableListOf<Beach>()
    val beachesLiveData = MutableLiveData<List<Beach>>()

    fun addBeach(name: String, city: String, state: String) {
        val beach = Beach(id = beaches.size, name = name, city = city, state = state)
        beaches.add(beach)
        beachesLiveData.value = beaches
    }

    fun removeBeach(beach: Beach) {
        beaches.remove(beach)
        beachesLiveData.value = beaches
    }
}