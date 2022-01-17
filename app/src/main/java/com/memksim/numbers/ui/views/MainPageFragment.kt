package com.memksim.numbers.ui.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.memksim.numbers.R
import com.memksim.numbers.databinding.FragmentMainPageBinding
import com.memksim.numbers.ui.stateholders.MainPageViewModel
import kotlin.random.Random

class MainPageFragment: Fragment(R.layout.fragment_main_page) {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainPageViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainPageBinding.bind(view)

        viewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        viewModel.getSpecificFact(viewModel.liveData.value!!.digit)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            //val text = it?.text ?: "error"
            binding.factText.text = it.fact
            binding.numberText.setText(it.digit.toString())
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




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}