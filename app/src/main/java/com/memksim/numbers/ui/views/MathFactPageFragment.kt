package com.memksim.numbers.ui.views

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentMathFactBinding
import com.memksim.numbers.ui.stateholders.FactViewModel

class MathFactPageFragment: Fragment(R.layout.fragment_math_fact) {

    private var _binding: FragmentMathFactBinding? = null
    private val binding: FragmentMathFactBinding
        get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var viewModel: FactViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMathFactBinding.bind(view)

        navController = findNavController()

        viewModel = ViewModelProvider(this)[FactViewModel::class.java]
        viewModel.getSpecificFact(
            requestCode = 1,
            number = viewModel.liveData.value!!.digit
        )

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            binding.factText.text = it.fact
            binding.numberText.setText(it.digit.toString())
        })

        binding.toolBar.setNavigationOnClickListener(View.OnClickListener {
            goBack()
        })

        binding.plus.setOnClickListener {
            viewModel.increaseDigit()
        }

        binding.minus.setOnClickListener {
            viewModel.decreaseDigit()
        }

        binding.search.setOnClickListener {
            getSpecificFact()
        }

        binding.searchRandom.setOnClickListener {
            getRandomFact()
        }

        binding.numberText.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(p1 == EditorInfo.IME_ACTION_DONE){
                    if(binding.numberText.text.toString() != ""){
                        viewModel.updateDigit(binding.numberText.text.toString().toInt())
                        viewModel.getSpecificFact(
                            requestCode = 1,
                            number = viewModel.liveData.value!!.digit
                        )
                        return true
                    }

                }
                return false
            }
        })



    }

    private fun getRandomFact(){
        viewModel.getRandomFact(1)
    }

    private fun getSpecificFact(){
        viewModel.getSpecificFact(
            requestCode = 1,
            viewModel.liveData.value!!.digit)
    }

    private fun goBack(){
        navController.navigate(R.id.action_mathFactPageFragment_to_mainPageFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}