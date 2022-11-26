package com.example.llogaritesirryms.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.databinding.LandingFragmentBinding

class LoginFragment : Fragment() {
    lateinit var binding: LandingFragmentBinding
    lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LandingFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginButton.setOnClickListener {
            login()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Preferences.init(requireContext())
        onBackPressed()
        initUI()

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_landingFragment_to_registerFragment)
        }
    }


    fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Do you want to exit out of the app?")
                    .setNegativeButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            requireActivity().finish()
                        })
                    .setPositiveButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }).create().show()
            }
        }
    }

    private fun login() {
        val name = binding.userName.text.toString()
        val passWord = binding.password.text.toString()
        context?.let { it1 ->
            viewModel.getLoginDetails(name, passWord)?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    if (binding.rememberMeCheckBox.isChecked) {
                        Preferences.saveName(name)
                        Preferences.savePassword(passWord)
                    }
                    val action = LoginFragmentDirections.actionLandingFragmentToCalcFragment(name)
                    findNavController().navigate(action)
                } else {
                    loginError()
                }
            })
        }
    }

    private fun loginError() {
        binding.loginError.alpha = 0f
        binding.loginError.visibility = View.VISIBLE
        binding.loginError.animate().apply {
            duration = 1500
            alpha(0.8f)
            Handler(Looper.myLooper()!!).postDelayed({
                binding.loginError.animate().apply {
                    alpha(0f)
                }
            }, 3000)
        }
    }

    private fun clearFields() {
        binding.userName.setText("", TextView.BufferType.EDITABLE)
        binding.password.setText("", TextView.BufferType.EDITABLE)

    }

    private fun initUI() {
        binding.userName.setText(Preferences.getName(), TextView.BufferType.EDITABLE)
        binding.password.setText(Preferences.getPassword(), TextView.BufferType.EDITABLE)
        if (binding.userName.text.toString() != Preferences.getName() || binding.password.text.toString() != Preferences.getPassword()) {
            clearFields()
        }
    }
}