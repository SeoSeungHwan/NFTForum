package com.router.nftforum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakaobrain.pathfinder_prodo.viewmodel.base.BaseMyRepositoryViewModel
import com.router.nftforum.binding.NaverNewsHolderModel
import com.router.nftforum.model.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(override val myRepository: MyRepository): BaseMyRepositoryViewModel() {

    private val _naverNewsListLiveData = MutableLiveData<List<NaverNewsHolderModel>>()
    val naverNewsListLiveData: LiveData<List<NaverNewsHolderModel>>
        get() = _naverNewsListLiveData

    fun fetchNaverNews(query: String, display: Int, start: Int, sort: String) {
            CoroutineScope(Dispatchers.IO).launch {
                myRepository.getNaverNews(query,display,start,sort).let {
                        response ->
                    _naverNewsListLiveData.postValue(response.items)
                }
            }
    }
}