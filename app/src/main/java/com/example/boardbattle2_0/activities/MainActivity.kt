package com.example.boardbattle2_0.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.boardbattle2_0.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}