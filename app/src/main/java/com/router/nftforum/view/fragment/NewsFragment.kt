package com.router.nftforum.view.fragment

import androidx.lifecycle.ViewModelProvider
import com.kakaobrain.pathfinder_prodo.viewmodel.viewmodelfactory.MyRepositoryViewModelFactory
import com.router.nftforum.R
import com.router.nftforum.databinding.FragmentNewsBinding
import com.router.nftforum.model.repository.MyRepository
import com.router.nftforum.view.base.BaseFragmentForViewBinding
import com.router.nftforum.viewmodel.NewsViewModel


class NewsFragment: BaseFragmentForViewBinding<FragmentNewsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_news

    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProvider(this, MyRepositoryViewModelFactory(MyRepository())).get(
            NewsViewModel::class.java
        )
    }
    override fun init() {
        viewDataBinding.viewModel = newsViewModel
        fetchNaverNews()
    }

    fun fetchNaverNews(){
        newsViewModel.fetchNaverNews("NFT",2,1,"date")
    }

}