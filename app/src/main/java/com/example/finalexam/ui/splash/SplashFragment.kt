package com.example.finalexam.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentSplashBinding
import com.example.finalexam.ui.menu.MenuFragmentDirections

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        doNavigation()
        return binding.root
    }

    private fun doNavigation() {
        val prefs: SharedPreferences? = activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
        val user = prefs?.getString(getString(R.string.username), null)
        val action: NavDirections = if (user == null) {
            SplashFragmentDirections.actionSplashFragmentToFragmentLogin()
        } else {
            SplashFragmentDirections.actionSplashFragmentToMenuFragment()
        }
        Handler(Looper.myLooper()!!).postDelayed({
            binding.root.findNavController().navigate(
                action,
                NavOptions.Builder()
                    .setPopUpTo(findNavController().graph.startDestinationId, true)
                    .build(),
            )
        }, 2000
        )
    }
}