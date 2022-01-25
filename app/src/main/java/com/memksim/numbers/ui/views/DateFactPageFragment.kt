package com.memksim.numbers.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentDateFactBinding

class DateFactPageFragment: Fragment(R.layout.fragment_date_fact) {

    private var _binding: FragmentDateFactBinding? = null
    private val binding: FragmentDateFactBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDateFactBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}