package com.example.a15.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a15.R
import com.example.a15.data.models.Contact
import com.example.a15.databinding.FragmentChangeContactBinding

class ChangeContactFragment : Fragment() {
    private var _binding: FragmentChangeContactBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ChangeContactFragmentArgs>()

    private val viewModel : StartFragmentViewModel by lazy { ViewModelProvider(requireActivity())[StartFragmentViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeContactBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contactName.text = args.contactData.name
        binding.editNote.setText(args.contactData.note)
        binding.button.setOnClickListener {

            val updatedContact = Contact(args.contactData.id,args.contactData.name,binding.editNote.text.toString())
            Log.i("updatedContact",updatedContact.toString())
            viewModel.updateContact(updatedContact)
            Toast.makeText(requireContext(),"Contact has been updated",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_changeContactFragment_to_startFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}