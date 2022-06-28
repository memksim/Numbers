package com.memksim.numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.numbers.databinding.ActivityMainBinding

/**http://numbersapi.com*/


//некоторые изменения

const val TAG = "test"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //при повороте экрана гарантируем, что не добавим повторно фракмент который уже был добавлен
        /*if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.container, MainPageFragment()).commit()
        }*/


    }
}