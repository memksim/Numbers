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
import com.memksim.numbers.databinding.FragmentDateFactBinding
import com.memksim.numbers.model.Fact
import com.memksim.numbers.ui.stateholders.FactViewModel

class DateFactPageFragment: Fragment(R.layout.fragment_date_fact) {

    private var _binding: FragmentDateFactBinding? = null
    private val binding: FragmentDateFactBinding
        get() = _binding!!

    private lateinit var viewModel: FactViewModel

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDateFactBinding.bind(view)

        viewModel = ViewModelProvider(this)[FactViewModel::class.java]

        navController = findNavController()

        viewModel = ViewModelProvider(this)[FactViewModel::class.java]
        viewModel.getSpecificFact(2)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            binding.factText.setText(it.fact)
            binding.dayText.setText(it.day.toString())
            binding.monthText.setText(it.month.toString())
        })

        binding.toolBar.setNavigationOnClickListener(View.OnClickListener {
            goBack()
        })

        binding.plus.setOnClickListener {
            viewModel.increaseDay()
        }

        binding.minus.setOnClickListener {
            viewModel.decreaseDay()
        }

        binding.search.setOnClickListener {
            getSpecificFact()
        }

        binding.searchRandom.setOnClickListener {
            getRandomFact()
        }

        binding.dayText.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(p1 == EditorInfo.IME_ACTION_DONE){
                    if(binding.dayText.text.toString() != ""){
                        viewModel.updateDate(
                            day = binding.dayText.text.toString().toInt()
                        )
                        viewModel.getSpecificFact(
                            requestCode = 2
                        )
                        return true
                    }

                }
                return false
            }
        })

        binding.monthText.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if(p1 == EditorInfo.IME_ACTION_DONE){
                    if(binding.monthText.text.toString() != ""){
                        viewModel.updateDate(
                            month = binding.monthText.text.toString().toInt()
                        )
                        viewModel.getSpecificFact(
                            requestCode = 2
                        )
                        return true
                    }

                }
                return false
            }
        })

    }

    private fun getRandomFact(){
        viewModel.getRandomFact(2)
    }

    private fun getSpecificFact(){
        viewModel.getSpecificFact(requestCode = 2)
    }

    private fun goBack(){
        navController.navigate(R.id.action_dateFactPageFragment_to_mainPageFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}