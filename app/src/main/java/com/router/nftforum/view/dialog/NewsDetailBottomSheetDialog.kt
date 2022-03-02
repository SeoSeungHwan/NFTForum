package com.router.nftforum.view.dialog


import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.router.nftforum.R
import com.router.nftforum.databinding.DialogBottomSheetNewsDetailBinding
import com.router.nftforum.view.base.BaseBottomSheetDialogFragment


class NewsDetailBottomSheetDialog :
    BaseBottomSheetDialogFragment<DialogBottomSheetNewsDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_bottom_sheet_news_detail


    private val webViewUrl: String by lazy {
        arguments?.getString("url").toString()
    }

    override fun init() {
        initWebview()
    }

    private fun initWebview(){
        viewDataBinding.webview.apply {
            webViewClient = WebViewClient()
            if(webViewUrl != null){
                loadUrl(webViewUrl)
            }else{
                Toast.makeText(context,"원본 기사를 찾을 수 없습니다.",Toast.LENGTH_SHORT).show()
            }
            settings.apply {
                javaScriptEnabled = false
                domStorageEnabled = false
                setSupportMultipleWindows(false)
            }
        }
    }
}