package com.example.llogaritesirryms.UI

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.databinding.AddValuesDialogBinding
import com.example.llogaritesirryms.databinding.CalcFragmentBinding
import kotlin.math.roundToInt

class CalculateFragment : Fragment() {

    lateinit var binding: CalcFragmentBinding

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




        binding.addButton.setOnClickListener {
            showDialog()
        }

        binding.shtoButton.setOnClickListener {
            initUI()
        }

        binding.kalkButton.setOnClickListener {

        }

    }


    private fun showDialog() {
        val dialogBinding = AddValuesDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = Dialog(requireActivity())
        dialog.setContentView(dialogBinding.root)

        val layoutParams = dialog.window!!.attributes
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = layoutParams
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

    private fun calcVal(): Int {
        val a1EKaluar = binding.a1EKaluar.text.toString().toDouble()
        val a2EKaluar = binding.a2EKaluar.text.toString().toDouble()
        val a1ETashme = binding.a1ETashme.text.toString().toDouble()
        val a2ETashme = binding.a2ETashme.text.toString().toDouble()
        val a1 = (a1ETashme - a1EKaluar) * 0.0675
        val a2 = (a2ETashme - a2EKaluar) * 0.0289
        var rezultati = a1 + a2
        return rezultati.roundToInt()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Do you want to go back to the login screen?").setNegativeButton(
                        "No",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }).setPositiveButton("Yes",
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
                        }).create().show()
            }
        }
    }


}