package com.example.lifesumtestapp.fooditem.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lifesumtestapp.core.ProvidersHolder
import com.example.lifesumtestapp.databinding.FragmentFoodItemBinding
import com.example.lifesumtestapp.fooditem.di.FoodItemScreenComponent
import com.example.lifesumtestapp.fooditem.presentation.model.ViewModelState
import com.example.lifesumtestapp.fooditem.presentation.shake.ShakeEventListener
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FoodItemFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<FoodItemViewModel> { coreViewModelFactory }

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
            viewModel.reload()
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        observeViewModelState()
    }

    private fun observeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state: ViewModelState ->
                    handlViewModelState(state)
                }
            }
        }
    }

    private fun handlViewModelState(state: ViewModelState) {
        when (state) {
            is ViewModelState.ErrorState -> {
                // todo show error
            }
            is ViewModelState.LoadedState -> {
                binding.foodItemCircle.populate(state.foodItem.foodItemCircleModel)
                binding.foodCompositionCarbs.populate(state.foodItem.carbs)
                binding.foodCompositionProtein.populate(state.foodItem.protein)
                binding.foodCompositionFat.populate(state.foodItem.fat)
            }
            is ViewModelState.LoadingState -> {
                // todo show loading
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