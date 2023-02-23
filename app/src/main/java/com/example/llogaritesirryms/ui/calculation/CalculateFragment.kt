package com.example.llogaritesirryms.ui.calculation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar.LayoutParams
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.data.calc.CalcPackage
import com.example.llogaritesirryms.databinding.AddValuesDialogBinding
import com.example.llogaritesirryms.databinding.CalcFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.result_fragment.*
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class CalculateFragment : Fragment() {


    lateinit var binding: CalcFragmentBinding
    private val safeArgs by navArgs<CalculateFragmentArgs>()
    lateinit var userName : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = CalcFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Preferences.init(requireContext())
        onBackPressed()
        userName = safeArgs.username

        binding.addButton.setOnClickListener {
            showDialog()
        }

        binding.shtoButton.setOnClickListener {
            initUI()
        }

        binding.kalkButton.setOnClickListener {
            try {
                calcVal()
            } catch (e: IllegalArgumentException) {
                Toast.makeText(
                    requireContext(),
                    "Ju lutem mbushni te gjitha fushat",
                    Toast.LENGTH_SHORT
                ).apply {
                    setGravity(Gravity.BOTTOM, 0, 65)
                }.show()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calcVal() {
        val a1EKaluar = binding.a1EKaluar.text.toString().toInt()
        val a2EKaluar = binding.a2EKaluar.text.toString().toInt()
        val a1ETashme = binding.a1ETashme.text.toString().toInt()
        val a2ETashme = binding.a2ETashme.text.toString().toInt()

        val calcpackage = CalcPackage(a1EKaluar,a1ETashme,a2EKaluar,a2ETashme,userName)

        val action = CalculateFragmentDirections.actionCalcFragmentToResultFragment(calcpackage)
        findNavController().navigate(action)


//        val roundedRes = homeViewModel.calcVal(a1ETashme, a1EKaluar, a2ETashme, a2EKaluar)
//
//        val dialogBinding = CalcResultDialogBinding.inflate(requireActivity().layoutInflater)
//        val dialog = Dialog(requireContext())
//        val parameters = LayoutParams(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
//        dialog.setContentView(dialogBinding.root, parameters)
//        dialog.window?.apply {
//            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            setWindowAnimations(R.style.DialogAnimation)
//        }
//        dialogBinding.calcResult.text = "Borgji total eshte : $roundedRes $"
//        dialogBinding.closeDialog.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialogBinding.closeDialogButton.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
    }


    private fun showDialog() {
        val dialogBinding = AddValuesDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = Dialog(requireActivity())
        val parameters = LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setContentView(dialogBinding.root,parameters)
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setWindowAnimations(R.style.DialogAnimation)
        }
        dialogBinding.ruajVlerat.setOnClickListener {
            try {
                val A1 = dialogBinding.a1EKaluar.text.toString().toInt()
                val A2 = dialogBinding.a2EKaluar.text.toString().toInt()
                Preferences.saveA1(A1)
                Preferences.saveA2(A2)
                dialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    "Vlerat u ruajtën me sukses, per ti perdorur shtyp butonin 'Shto vlerat e ruajtura'",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Plotesoni te gjitha fushat!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialogBinding.closeDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initUI() {
//        Gets the saved A1 and A2 values from Shared Preferences, if there's any
        binding.a1EKaluar.setText(Preferences.getA1().toString(), TextView.BufferType.EDITABLE)
        binding.a2EKaluar.setText(Preferences.getA2().toString(), TextView.BufferType.EDITABLE)
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Do you want to go back to the login screen?")
                    .setNegativeButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            fragmentManager?.commit {
                                setCustomAnimations(
                                    R.anim.slide_in_left,
                                    R.anim.slide_out_right,
                                    R.anim.slide_in_right,
                                    R.anim.slide_out_left
                                )
                                findNavController().navigate(R.id.action_calcFragment_to_landingFragment)
                            }
                        })
                    .setPositiveButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }).create().show()
            }
        }
    }

//    private fun backGroundAnimation() {
//        val layout = binding.calcLayout
//        val animation = layout.background as AnimationDrawable
//        animation.setEnterFadeDuration(2500)
//        animation.setExitFadeDuration(1000)
//        animation.start()
//    }


}