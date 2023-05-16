package com.example.chess.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.R
import com.example.chess.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.btnPlay.setOnClickListener { launchGameScreen() }
        binding.btnCredits.setOnClickListener { launchCreditsScreen() }

        return binding.root
    }

    private fun launchGameScreen() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container_for_fragments, GameFragment())
            .commit()
    }

    private fun launchCreditsScreen() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container_for_fragments, InfoFragment())
            .addToBackStack("backstack")
            .commit()
    }
}