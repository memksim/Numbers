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
import com.memksim.numbers.databinding.FragmentTriviaFactBinding
import com.memksim.numbers.ui.stateholders.TriviaFactPageViewModel

class TriviaFactPageFragment: Fragment(R.layout.fragment_trivia_fact) {

    private var _binding: FragmentTriviaFactBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TriviaFactPageViewModel

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTriviaFactBinding.bind(view)

        navController = findNavController()

        viewModel = ViewModelProvider(this)[TriviaFactPageViewModel::class.java]
        viewModel.getSpecificFact(viewModel.liveData.value!!.digit)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            //val text = it?.text ?: "error"
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
                        viewModel.getSpecificFact(viewModel.liveData.value!!.digit)
                        return true
                    }

                }
                return false
            }
        })

    }

    fun getRandomFact(){
        viewModel.getRandomFact()
    }

    fun getSpecificFact(){
        viewModel.getSpecificFact(viewModel.liveData.value!!.digit)
    }

    fun goBack(){
        navController.navigate(R.id.action_triviaFactPageFragment_to_mainPageFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}