package com.example.lifesumtestapp.fooditem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.lifesumtestapp.R
import com.example.lifesumtestapp.core.ProvidersHolder
import com.example.lifesumtestapp.databinding.FragmentFirstBinding
import com.example.lifesumtestapp.fooditem.di.FoodItemScreenComponent
import javax.inject.Inject

class FoodItemFragment : Fragment() {

    @Inject
    lateinit var coreViewModelFactory: AbstractSavedStateViewModelFactory
    private val viewModel by viewModels<FoodItemViewModel> { coreViewModelFactory }

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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
        FoodItemScreenComponent.create(accumulator, this)
            .inject(this)
    }
}