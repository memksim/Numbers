package com.memksim.numbers.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentMathFactBinding

class MathFactPageFragment: Fragment(R.layout.fragment_math_fact) {

    private var _binding: FragmentMathFactBinding? = null
    private val binding: FragmentMathFactBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMathFactBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}