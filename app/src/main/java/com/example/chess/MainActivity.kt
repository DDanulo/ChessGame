package com.example.chess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chess.fragments.MainFragment
import com.example.chess.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container_for_fragments, MainFragment())
            .commit()

    }
}