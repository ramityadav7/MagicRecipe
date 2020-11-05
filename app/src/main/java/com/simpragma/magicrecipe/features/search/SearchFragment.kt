package com.simpragma.magicrecipe.features.search

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexboxLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.simpragma.magicrecipe.MainActivity
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.application.MagicRecipeApplication
import com.simpragma.magicrecipe.core.CoreFragment
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : CoreFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MagicRecipeApplication).appComponent.searchComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as MainActivity).hideBackIcon()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       initializeView()
    }

    private fun initializeView() {
        compositeDisposable.add(RxView.clicks(imageViewAdd)
            .subscribe { handleAdd() })
        compositeDisposable.add(RxView.clicks(buttonSearch)
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { handleSearch() })

        compositeDisposable.add(RxTextView.textChanges(editTextSearch)
            .subscribe{handleSearchTextChange()})
    }

    private fun handleSearchTextChange() {
        var text = editTextSearch.text.toString()
        imageViewAdd.isEnabled = !TextUtils.isEmpty(text)
    }

    private fun handleAdd() {
        val inflatedView = View.inflate(context, R.layout.ingredient_item, null)
        var param : FlexboxLayout.LayoutParams = FlexboxLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        param.marginEnd = resources.getDimension(R.dimen.default_short_margin).toInt()
        param.marginStart = param.marginEnd
        inflatedView.layoutParams = param
        inflatedView.findViewById<TextView>(R.id.textViewIngredient).text = editTextSearch.text.toString()
        val deleteIV = inflatedView.findViewById<ImageView>(R.id.imageViewDelete)
        compositeDisposable.add(RxView.clicks(deleteIV)
            .subscribe { handleDelete(deleteIV) })
        fblSearch.addView(inflatedView)

        editTextSearch.setText("")
        handleSearchButtonState()
    }

    private fun handleSearch() {
        viewModel.handleSearch(fblSearch.children, findNavController())
    }

    private fun handleDelete(view: View) {
        fblSearch.removeView(view.parent as View)
        handleSearchButtonState()
    }

    private fun handleSearchButtonState() {
        buttonSearch.isEnabled = fblSearch.childCount > 0
    }
}