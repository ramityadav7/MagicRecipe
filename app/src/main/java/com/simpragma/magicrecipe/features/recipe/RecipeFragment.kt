package com.simpragma.magicrecipe.features.recipe

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simpragma.magicrecipe.MainActivity
import com.simpragma.magicrecipe.R
import com.simpragma.magicrecipe.application.MagicRecipeApplication
import com.simpragma.magicrecipe.common.AppConstant
import com.simpragma.magicrecipe.core.CoreFragment
import com.simpragma.magicrecipe.features.recipe.model.RecipeUiModel
import com.simpragma.magicrecipe.network.model.Result
import kotlinx.android.synthetic.main.fragment_recipe.*
import javax.inject.Inject

class RecipeFragment : CoreFragment(), ItemClickHandler {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<RecipeViewModel> { viewModelFactory }
    private lateinit var searchQuery : String
    private lateinit var recipeRVAdapter: RecipeRVAdapter
    private var recipeData = mutableListOf<Result>()

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
        recipeRVAdapter = RecipeRVAdapter(recipeData, context?.applicationContext!!, this)
        recipeRecyclerView.adapter = recipeRVAdapter
        viewModel.getRecipe(searchQuery)
    }

    private fun setUpRx() {
        viewModel.recipeLiveData.observe(viewLifecycleOwner, Observer { recipeUiModel : RecipeUiModel ->

            if(recipeUiModel.showProgress) {
                progressBarRecipe.visibility = View.VISIBLE
                recipeRecyclerView.visibility = View.GONE
            } else {
                progressBarRecipe.visibility = View.GONE
                recipeRecyclerView.visibility = View.VISIBLE
            }

            recipeData.clear()
            if(recipeUiModel.recipeList != null)
                recipeData.addAll(recipeUiModel.recipeList?.toMutableList()!!)
        })
    }

    override fun handleItemClick(name: String) {
        TODO("Not yet implemented")
    }
}