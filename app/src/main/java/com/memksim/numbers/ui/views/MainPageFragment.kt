package com.memksim.numbers.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentMainPageBinding

class MainPageFragment: Fragment(R.layout.fragment_main_page) {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainPageBinding.bind(view)
        activity?.actionBar?.hide()
        navController = findNavController()

        binding.triviaFactButton.setOnClickListener {
            goToTrivia()
        }

        binding.mathFactButton.setOnClickListener {
            goToMath()
        }

        binding.dateFactButton.setOnClickListener {
            goToDate()
        }
    }

    fun goToTrivia(){
        navController.navigate(R.id.action_mainPageFragment_to_triviaFactPageFragment)
    }

    fun goToMath(){
        navController.navigate(R.id.action_mainPageFragment_to_mathFactPageFragment)
    }

    fun goToDate(){
        navController.navigate(R.id.action_mainPageFragment_to_dateFactPageFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}