package com.memksim.numbers.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentMainPageBinding
import com.memksim.numbers.ui.stateholders.MainPageViewModel

class MainPageFragment: Fragment(R.layout.fragment_main_page) {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainPageViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainPageBinding.bind(view)

        viewModel = ViewModelProvider(this)[MainPageViewModel::class.java]

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            val text = it?.text ?: "error"
            binding.fact.text = text
        })

        binding.trivia.setOnClickListener {
            viewModel.getRandomFact()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}