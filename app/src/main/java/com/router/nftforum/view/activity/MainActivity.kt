package com.router.nftforum.view.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.router.nftforum.R
import com.router.nftforum.databinding.ActivityMainBinding
import com.router.nftforum.view.base.BaseActivityForViewBinding

class MainActivity : BaseActivityForViewBinding<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun init() {
        initBottomNavigation()
        initAdMob()
    }

    private fun initBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewDataBinding.bottomNavigationView.setupWithNavController(navController)
    }
    
    private fun initAdMob(){
        //Admob 초기화 및 load
        MobileAds.initialize(this) {}
        
        val adRequest = AdRequest.Builder().build()
        viewDataBinding.adView.loadAd(adRequest)
    }
}