package com.example.chess.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chess.R
import com.example.chess.databinding.FragmentInfoBinding
import com.example.chess.databinding.FragmentMainBinding

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        return binding.root
    }


}