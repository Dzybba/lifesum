package com.example.lifesumtestapp.fooditemdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.core.ProvidersHolder
import com.example.lifesumtestapp.databinding.FragmentFoodDetailsBinding
import com.example.lifesumtestapp.fooditemdetails.di.FoodDetailsScreenComponent
import com.example.lifesumtestapp.fooditemdetails.presentation.adapter.FoodDetailsAdapter
import com.example.lifesumtestapp.fooditemdetails.presentation.adapter.MarginItemDecoration
import com.example.lifesumtestapp.fooditemdetails.presentation.mapper.FoodDetailsStateToRenderMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodDetailsFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<FoodDetailsViewModel> { coreViewModelFactory }

    @Inject
    lateinit var renderMapper: FoodDetailsStateToRenderMapper


    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!

    private var adapter: FoodDetailsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FoodDetailsAdapter()
        binding.foodDetailsRecycler.adapter = adapter
        val marginItemDecoration = MarginItemDecoration(
            0,
            resources.getDimensionPixelSize(R.dimen.food_details_vertical_margin)
        )
        binding.foodDetailsRecycler.addItemDecoration(marginItemDecoration)
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getUiState().collect {
                        val render = renderMapper.map(resources, it)
                        adapter?.submitList(render.detailsList)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accumulator =
            (requireActivity().application as ProvidersHolder).getProvidersAccumulator()
        val initData =
            requireArguments().getSerializable(FoodDetailInitialData.ARGUMENT_KEY) as FoodDetailInitialData
        FoodDetailsScreenComponent.create(accumulator, this, initData)
            .inject(this)
    }
}