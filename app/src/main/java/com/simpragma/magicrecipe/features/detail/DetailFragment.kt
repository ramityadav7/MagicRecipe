package com.simpragma.magicrecipe.features.detail

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.simpragma.magicrecipe.MainActivity
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.application.MagicRecipeApplication
import com.simpragma.magicrecipe.common.AppConstant
import com.simpragma.magicrecipe.core.CoreFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : CoreFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }
    private lateinit var url : String

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MagicRecipeApplication).appComponent.detailComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.detail)
        (activity as MainActivity).showBackIcon()
        url = arguments?.getString(AppConstant.BUNDLE_URL)!!
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBarDetail.visibility = View.VISIBLE
        webViewDetail.loadUrl(url)

        webViewDetail.webViewClient = (object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBarDetail.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBarDetail.visibility = View.GONE
            }
        })
    }
}