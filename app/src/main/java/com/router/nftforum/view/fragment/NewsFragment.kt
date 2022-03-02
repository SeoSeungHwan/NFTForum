package com.router.nftforum.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakaobrain.pathfinder_prodo.viewmodel.viewmodelfactory.MyRepositoryViewModelFactory
import com.router.nftforum.R
import com.router.nftforum.adapter.NewsRecyclerViewAdapter
import com.router.nftforum.binding.NaverNewsHolderModel
import com.router.nftforum.databinding.FragmentNewsBinding
import com.router.nftforum.model.repository.MyRepository
import com.router.nftforum.util.ViewUtil
import com.router.nftforum.view.base.BaseFragmentForViewBinding
import com.router.nftforum.view.dialog.NewsDetailBottomSheetDialog
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
        setUpObserver()

    }

    private fun fetchNaverNews(){
        newsViewModel.fetchNaverNews("NFT",20,1,"date")
        ViewUtil().showLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
    }

    private fun setUpObserver(){
        newsViewModel.naverNewsListLiveData.observe(viewLifecycleOwner){
            setUpNewsRecyclerView(it)
        }
    }
    private fun setUpNewsRecyclerView(hideList: List<NaverNewsHolderModel>) {
        viewDataBinding.newsRecyclerview.apply {
            adapter = NewsRecyclerViewAdapter(hideList, ::clickNews)
            layoutManager = LinearLayoutManager(context)
        }
        ViewUtil().hideLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
    }

    private fun clickNews(url: String) {
        NewsDetailBottomSheetDialog().apply {
            arguments = Bundle().apply {
                putString("url", url)
            }
        }.show(childFragmentManager, "CardDetail")
    }

}