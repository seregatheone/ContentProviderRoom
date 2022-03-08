package com.example.a15.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a15.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val viewModel : StartFragmentViewModel by lazy { ViewModelProvider(requireActivity())[StartFragmentViewModel::class.java] }

    private var _adapter : RecViewAdapter? = RecViewAdapter()
    private val adapter = _adapter!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater,container,false)
        checkPermissions()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = binding.recView
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readAllData.observe(viewLifecycleOwner
        ) {
            adapter.setContactListData(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun checkPermissions() {

        val permission =
            ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequestForPermission()
        }
    }

    private fun makeRequestForPermission() {

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.READ_CONTACTS),
            1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "Accept permission request", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> Toast.makeText(requireContext(), "Accept denied request", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        _adapter = null
        super.onDestroyView()
    }
}