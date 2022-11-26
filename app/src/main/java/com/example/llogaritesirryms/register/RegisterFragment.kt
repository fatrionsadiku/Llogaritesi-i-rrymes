package com.example.llogaritesirryms.register

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.User
import com.example.llogaritesirryms.data.UserDatabase
import com.example.llogaritesirryms.databinding.RegisterFragmentBinding
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class RegisterFragment : Fragment() {
    lateinit var binding : RegisterFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            registerUser()
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_landingFragment)
        }
    }



    private fun registerUser() {
        val userName = binding.userName.text.toString()
        val password = binding.password.text.toString()
        val password1 = binding.reEnterPassword.text.toString()

        val user = User(
            null,
            userName,
            password
        )
        if (userName.isNotEmpty() && password.isNotEmpty() && password == password1) {
            val database: UserDatabase = UserDatabase.getDatabase(requireContext())
            val userDao = database.userDao()
            GlobalScope.launch(Dispatchers.IO) {
                userDao.addUser(user)
            }
            registerSuccessfulAnimation()
            clearFields()
        }
    }



    private fun registerSuccessfulAnimation(){
        binding.registerSuccesful.alpha = 0f
        binding.registerSuccesful.visibility = View.VISIBLE
        binding.registerSuccesful.animate().apply {
            duration = 1500
            alpha(0.8f)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.registerSuccesful.animate().apply {
                    alpha(0f)
                }
            }, 3000)
        }
    }

    fun registerFailedAnimation(){
        binding.registerSuccesful.alpha = 0f
        binding.registerSuccesful.setTextColor(Color.RED)
        binding.registerSuccesful.text = "Useri nuk u regjistrua, provo perseri"
        binding.registerSuccesful.visibility = View.VISIBLE
        binding.registerSuccesful.animate().apply {
            duration = 1500
            alpha(0.8f)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.registerSuccesful.animate().apply {
                    alpha(0f)
                }
            }, 3000)
        }
    }

    fun clearFields(){
        binding.password.setText("")
        binding.userName.setText("")
        binding.reEnterPassword.setText("")
    }




}