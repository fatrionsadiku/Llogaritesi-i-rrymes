package com.example.llogaritesirryms.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.MainActivity
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.databinding.SplashScreenFragmentBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding : SplashScreenFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logo.alpha = 0f
        binding.appTitle.alpha = 0f
        binding.appTitle.animate().setDuration(2000).alpha(1f)
        binding.logo.animate().setDuration(2000).alpha(1f).withEndAction{
            Intent(this,MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }
        }
    }
}