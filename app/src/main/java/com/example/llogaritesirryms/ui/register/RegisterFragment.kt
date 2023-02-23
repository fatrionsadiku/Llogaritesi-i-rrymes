package com.example.llogaritesirryms.ui.register

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.user.User
import com.example.llogaritesirryms.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding: RegisterFragmentBinding
    val registerViewModel : RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            registerUser()
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
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
        CoroutineScope(Dispatchers.IO).launch {
            if (userName.isNotEmpty() && password.isNotEmpty() && password == password1) {
                registerViewModel.registerUser(user)
                withContext(Dispatchers.Main) {
                    clearFields()
                    findNavController().navigate(R.id.action_registerFragment_to_landingFragment)
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(),"Useri nuk u regjistrua, provo perseri", Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.BOTTOM, 0, 65)
                    }.show()
                }
            }
        }
    }
//    private fun registerSuccessfulAnimation() {
//        binding.registerSuccesful.alpha = 0f
//        binding.registerSuccesful.visibility = View.VISIBLE
//        binding.registerSuccesful.animate().apply {
//            duration = 1500
//            alpha(0.8f)
//            Handler(Looper.myLooper()!!).postDelayed({
//                binding.registerSuccesful.animate().apply {
//                    alpha(0f)
//                }
//            }, 3000)
//        }
//    }

    fun clearFields() {
        binding.password.setText("")
        binding.userName.setText("")
        binding.reEnterPassword.setText("")
    }


}