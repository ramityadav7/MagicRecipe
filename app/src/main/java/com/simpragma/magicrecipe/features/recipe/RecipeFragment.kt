package com.simpragma.magicrecipe.features.recipe

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.simpragma.magicrecipe.MainActivity
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.application.MagicRecipeApplication
import com.simpragma.magicrecipe.common.AppConstant
import com.simpragma.magicrecipe.core.CoreFragment
import com.simpragma.magicrecipe.network.model.Result
import kotlinx.android.synthetic.main.fragment_recipe.*
import javax.inject.Inject


class RecipeFragment : CoreFragment(), ItemClickHandler {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RecipeViewModel> { viewModelFactory }
    private lateinit var searchQuery : String
    private lateinit var recipeRVAdapter: RecipeRVAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MagicRecipeApplication).appComponent.recipeComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        (activity as MainActivity).showBackIcon()
        searchQuery = arguments?.getString(AppConstant.BUNDLE_SEARCH_QUERY)!!
        setUpRx()
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {
        recipeRVAdapter = RecipeRVAdapter(this)
        recipeRecyclerView.adapter = recipeRVAdapter
    }

    private fun setUpRx() {
        viewModel.getRecipeLiveData(compositeDisposable, searchQuery).observe(viewLifecycleOwner, {
            recipeRVAdapter.submitList(it)
        })
    }

    override fun handleItemClick(url: String) {
        if(!TextUtils.isEmpty(url))
            viewModel.navigateDetailFragment(findNavController(), url)
    }
}