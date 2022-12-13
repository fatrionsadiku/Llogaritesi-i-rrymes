package com.example.llogaritesirryms.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.databinding.SplashScreenFragmentBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: SplashScreenFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashScreenFragmentBinding.inflate(inflater, container, false)

//            binding.logo.alpha = 0f
//            binding.appTitle.alpha = 0f
//            binding.appTitle.animate().setDuration(4000).alpha(1f)
//            binding.logo.animate().setDuration(4000).alpha(1f).withEndAction{
//                findNavController().navigate(R.id.action_splashScreenFragment_to_landingFragment)
//            }
        return binding.root


    }
}

