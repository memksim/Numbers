package com.memksim.numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memksim.numbers.ui.views.MainPageFragment
import com.memksim.numbers.databinding.ActivityMainBinding

/**http://numbersapi.com/#random/trivia*/

const val TAG = "test"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //при повороте экрана гарантируем, что не добавим повторно фракмент который уже был добавлен
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.container, MainPageFragment()).commit()
        }


    }
}