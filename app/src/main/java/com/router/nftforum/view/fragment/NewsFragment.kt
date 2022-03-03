package com.router.nftforum.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.tabs.TabLayout
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
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class NewsFragment: BaseFragmentForViewBinding<FragmentNewsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_news

    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProvider(this, MyRepositoryViewModelFactory(MyRepository())).get(
            NewsViewModel::class.java
        )
    }

    private var position : Int =0
    override fun init() {
        viewDataBinding.viewModel = newsViewModel
        fetchNaverNews("sim")
        setUpObserver()
        setUpBtnListener()
    }

    private fun fetchNaverNews(sort : String){
        newsViewModel.fetchNaverNews("NFT",100,1,sort)
        ViewUtil().showLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
    }


    private fun setUpBtnListener(){
        viewDataBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
                if (position == 0) {
                    fetchNaverNews("sim")
                } else if (position == 1) {
                    fetchNaverNews("date")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
    private fun setUpObserver(){
        newsViewModel.naverNewsListLiveData.observe(viewLifecycleOwner){
            setUpNewsRecyclerView(it)
        }
    }

    private fun setUpNewsRecyclerView(naverNews: List<NaverNewsHolderModel>) {
        viewDataBinding.newsRecyclerview.apply {
            adapter = NewsRecyclerViewAdapter(naverNews, ::clickNews)
            layoutManager = LinearLayoutManager(context)
        }
        ViewUtil().hideLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
    }

    private fun clickNews(url: String) {
        if(url.contains("segyebiz")){
            Toast.makeText(context,"해당 뉴스는 모바일 버전을 지원하지않습니다.",Toast.LENGTH_SHORT).show()
        }
        NewsDetailBottomSheetDialog().apply {
            arguments = Bundle().apply {
                putString("url", url)
            }
        }.show(childFragmentManager, "CardDetail")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.newsSwipeRefreshLayout.setOnRefreshListener {
            viewDataBinding.newsSwipeRefreshLayout.isRefreshing = false
            if (position == 0) {
                fetchNaverNews("sim")
            } else if (position == 1) {
                fetchNaverNews("date")
            }
        }
    }
}