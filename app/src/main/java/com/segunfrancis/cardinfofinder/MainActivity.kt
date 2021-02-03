package com.segunfrancis.cardinfofinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.segunfrancis.cardinfofinder.databinding.MainActivityBinding
import com.segunfrancis.cardinfofinder.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}