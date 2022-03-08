package com.example.a15.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a15.R
import com.example.a15.databinding.FragmentChangeContactBinding
import com.example.a15.databinding.FragmentStartBinding

class ChangeContactFragment : Fragment() {
    private var _binding: FragmentChangeContactBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeContactBinding.inflate(inflater,container,false)
        return binding.root
    }

}