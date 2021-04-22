package com.example.testapp.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.features.shibe.ui.ShibeFragment

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ShibeFragment.newInstance())
            .commit()

    }



}