package com.example.llogaritesirryms.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.llogaritesirryms.MainActivity
import com.example.llogaritesirryms.databinding.SplashScreenFragmentBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: SplashScreenFragmentBinding

    //    var animationState = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            logo.alpha = 0f
            appTitle.alpha = 0f
            splashScreenActivity.alpha = 0.7f
            appTitle.animate().setDuration(1000).alpha(1f)
            splashScreenActivity.animate().setDuration(1000).alpha(1f)
            logo.animate().setDuration(1000).alpha(1f).withEndAction {
                Intent(this@SplashScreenActivity, MainActivity::class.java).also {
                    startActivity(it)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        }

    }
}

