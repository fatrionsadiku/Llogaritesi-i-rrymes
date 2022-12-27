package com.example.llogaritesirryms.UI

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import android.widget.Toolbar.LayoutParams
import android.widget.Toolbar.OnMenuItemClickListener
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.databinding.AddValuesDialogBinding
import com.example.llogaritesirryms.databinding.CalcFragmentBinding
import com.example.llogaritesirryms.databinding.CalcResultDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

@AndroidEntryPoint
class CalculateFragment : Fragment() {


    lateinit var binding: CalcFragmentBinding
    val homeViewModel: HomeViewModel by viewModels()
    private val safeArgs by navArgs<CalculateFragmentArgs>()

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
        backGroundAnimation()

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
        val a1EKaluar = binding.a1EKaluar.text.toString().toDouble()
        val a2EKaluar = binding.a2EKaluar.text.toString().toDouble()
        val a1ETashme = binding.a1ETashme.text.toString().toDouble()
        val a2ETashme = binding.a2ETashme.text.toString().toDouble()

        val roundedRes = homeViewModel.calcVal(a1ETashme, a1EKaluar, a2ETashme, a2EKaluar)

        val dialogBinding = CalcResultDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = Dialog(requireContext())
        val parameters = LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setContentView(dialogBinding.root, parameters)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.calcResult.text = "Borgji total eshte : $roundedRes $"
        dialogBinding.closeDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.closeDialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun showDialog() {
        val dialogBinding = AddValuesDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(dialogBinding.root)
        dialogBinding.ruajVlerat.setOnClickListener {
            try {
                val A1 = dialogBinding.a1EKaluar.text.toString().toInt()
                val A2 = dialogBinding.a2EKaluar.text.toString().toInt()
                Preferences.saveA1(A1)
                Preferences.saveA2(A2)
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
        val A1 = Preferences.getA1().toString()
        val A2 = Preferences.getA2().toString()
        binding.a1EKaluar.setText(A1, TextView.BufferType.EDITABLE)
        binding.a2EKaluar.setText(A2, TextView.BufferType.EDITABLE)
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Do you want to go back?")
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

    private fun backGroundAnimation() {
        val layout = binding.calcLayout
        val animation = layout.background as AnimationDrawable
        animation.setEnterFadeDuration(2500)
        animation.setExitFadeDuration(1000)
        animation.start()
    }


}