package com.router.nftforum.view.dialog


import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.router.nftforum.R
import com.router.nftforum.databinding.DialogBottomSheetNewsDetailBinding
import com.router.nftforum.util.ViewUtil
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

    private fun initWebview() {


        viewDataBinding.webview.apply {
            webViewClient = WebViewClient()
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    ViewUtil().showLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    ViewUtil().hideLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
                }
            }

            if (webViewUrl != null) {
                loadUrl(webViewUrl)
            } else {
                Toast.makeText(context, "원본 기사를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }

            settings.apply {
                javaScriptEnabled = false
                domStorageEnabled = false
                layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
                setSupportMultipleWindows(false)
            }



        }
    }
}