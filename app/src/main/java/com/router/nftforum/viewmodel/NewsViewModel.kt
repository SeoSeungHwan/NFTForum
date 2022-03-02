package com.router.nftforum.viewmodel

import android.util.Log
import com.kakaobrain.pathfinder_prodo.viewmodel.base.BaseMyRepositoryViewModel
import com.router.nftforum.model.repository.MyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(override val myRepository: MyRepository): BaseMyRepositoryViewModel() {


    fun fetchNaverNews(query: String, display: Int, start: Int, sort: String) {
            CoroutineScope(Dispatchers.IO).launch {
                myRepository.getNaverNews(query,display,start,sort).let {
                        response ->


                }
            }
    }
}