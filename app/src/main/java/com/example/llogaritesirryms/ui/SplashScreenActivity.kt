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
//        binding.splashScreenActivity.setOnClickListener {
//            animationState = false
//            Intent(this, MainActivity::class.java).also {
//                startActivity(it)
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//                finish()
//            }
//        }
//        I'm trying to make it so whenever the splashscreen gets tapped the animation is skipped, i'm using a boolean field to check wether i have tapped the screen
//        or not. Skipping works but if i do skip it then the code below gets executed aswell, which shouldn't since the field is set to false whenever i tap on the screen
//        To be fixed!

        binding.logo.alpha = 0f
        binding.appTitle.alpha = 0f
        binding.appTitle.animate().setDuration(2000).alpha(1f)
        binding.logo.animate().setDuration(2000).alpha(1f).withEndAction {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}

