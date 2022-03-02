package com.router.nftforum.view.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.router.nftforum.R
import com.router.nftforum.databinding.ActivityMainBinding
import com.router.nftforum.view.base.BaseActivityForViewBinding

class MainActivity : BaseActivityForViewBinding<ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun init() {
        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewDataBinding.bottomNavigationView.setupWithNavController(navController)
    }
}