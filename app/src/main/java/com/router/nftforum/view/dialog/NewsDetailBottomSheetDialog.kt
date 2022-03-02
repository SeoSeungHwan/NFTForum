package com.router.nftforum.view.dialog


import android.content.ActivityNotFoundException
import android.graphics.Bitmap
import android.util.Log
import android.view.View
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
import android.content.Intent





class NewsDetailBottomSheetDialog :
    BaseBottomSheetDialogFragment<DialogBottomSheetNewsDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.dialog_bottom_sheet_news_detail


    private val webViewUrl: String by lazy {
        arguments?.getString("url").toString()
    }

    override fun init() {
        initWebview()
        setUpBtnListener()
    }

    private fun initWebview() {
        viewDataBinding.webview.apply {
            webViewClient = WebViewClient()
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    viewDataBinding.shareBtn.visibility = View.GONE
                    ViewUtil().showLoadingProgressBar(viewDataBinding.progressBar, activity?.window)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    viewDataBinding.shareBtn.visibility = View.VISIBLE
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

    private fun setUpBtnListener(){
        viewDataBinding.shareBtn.setOnClickListener {
            try {
                val mIntent = Intent()
                mIntent.action = Intent.ACTION_SEND
                mIntent.putExtra(Intent.EXTRA_TEXT, webViewUrl)
                mIntent.type = "text/plain"
                startActivity(Intent.createChooser(mIntent, "URL 공유하기"))
            } catch (ignored: ActivityNotFoundException) {
                Log.d("error", "ignored : $ignored")
            }
        }
    }
}