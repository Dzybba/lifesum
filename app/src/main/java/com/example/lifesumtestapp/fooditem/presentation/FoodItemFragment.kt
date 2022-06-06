package com.example.lifesumtestapp.fooditem.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.core.ProvidersHolder
import com.example.lifesumtestapp.databinding.FragmentFoodItemBinding
import com.example.lifesumtestapp.fooditem.di.FoodItemScreenComponent
import com.example.lifesumtestapp.fooditem.presentation.mapper.FoodItemDataToRenderMapper
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import com.example.lifesumtestapp.fooditem.presentation.shake.ShakeEventListener
import com.example.lifesumtestapp.fooditemdetails.presentation.FoodDetailInitialData
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FoodItemFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<FoodItemViewModel> { coreViewModelFactory }

    @Inject
    lateinit var renderMapper: FoodItemDataToRenderMapper

    private var sensorManager: SensorManager? = null
    private val shakeEventListener = ShakeEventListener {
        viewModel.reload()
    }

    private var _binding: FragmentFoodItemBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        _binding = FragmentFoodItemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            viewModel.onDetailsButtonClicked()
        }
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.uiState.collect { handleViewModelState(it) } }
                launch { viewModel.openDetailsFlow.collect { openDetailsScreen(it) } }
            }
        }
    }

    private fun openDetailsScreen(model: FoodDetailInitialData) {
        val bundle = bundleOf(FoodDetailInitialData.ARGUMENT_KEY to model)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    private fun handleViewModelState(state: ViewModelState) {
        when (state) {
            is ViewModelState.ErrorState -> {
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
                binding.mainContentGroup.isVisible = false
            }
            is ViewModelState.LoadedState -> {
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                binding.mainContentGroup.isVisible = true
                val render = renderMapper.map(resources, state.foodItemData)
                binding.foodItemCircle.populate(render.foodItemCircleModel)
                binding.foodCompositionCarbs.populate(render.carbs)
                binding.foodCompositionProtein.populate(render.protein)
                binding.foodCompositionFat.populate(render.fat)
            }
            is ViewModelState.LoadingState -> {
                binding.errorView.isVisible = false
                binding.mainContentGroup.isVisible = false
                binding.loadingView.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        val sensorManager = sensorManager ?: return
        sensorManager.registerListener(
            shakeEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        sensorManager?.unregisterListener(shakeEventListener)
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accumulator =
            (requireActivity().application as ProvidersHolder).getProvidersAccumulator()
        FoodItemScreenComponent.create(accumulator, this)
            .inject(this)
    }
}