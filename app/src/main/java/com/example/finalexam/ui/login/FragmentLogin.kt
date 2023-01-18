package com.example.finalexam.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val userInput = binding.userInput
        val continueBtn = binding.continueBtn

        continueBtn.setOnClickListener {
            val prefs: SharedPreferences? =
                activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
            if (userInput.text.isNotBlank()) {
                with(prefs!!.edit()) {
                    putString(getString(R.string.username), userInput.text.toString())
                    apply()
                }
                val navigation =
                    FragmentLoginDirections.actionFragmentLoginToMenuFragment()
                binding.root.findNavController().navigate(navigation)
            }
        }


    }
}