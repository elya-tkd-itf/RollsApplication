package com.dasonick.rollsapplication.ui.rolls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dasonick.rollsapplication.base.Roll
import com.dasonick.rollsapplication.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RollsViewModel : ViewModel() {
    private var data: MutableLiveData<List<Roll>>? = null
    private var repo = Repository()

    fun getData(): LiveData<List<Roll>> {
        if (data == null) {
            data = MutableLiveData()
            loadData()
        }
        return data as LiveData<List<Roll>>
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO){
            data?.postValue(repo.getRolls())
        }
    }
}