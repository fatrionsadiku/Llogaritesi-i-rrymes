package com.example.llogaritesirryms.ui.calculation_history

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color.alpha
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import com.example.llogaritesirryms.databinding.CalculationHistoryFragmentBinding
import com.example.llogaritesirryms.ui.CalcHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CalculationHistoryFragment() : Fragment() {
    lateinit var binding: CalculationHistoryFragmentBinding
    private val viewModel : CalcHistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CalculationHistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Preferences.init(requireContext())
        val calcAdapter = CalcHistoryAdapter()

        viewModel.calculations.observe(viewLifecycleOwner){
            calcAdapter.submitList(it)
        }
        binding.apply {
            deleteAllRecords.setOnClickListener {
                showDeleteAllConfirmationDialog()
            }
            calcHistoryRecyclerView.apply {
                adapter = calcAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                if(!Preferences.getValuesState()){
                    noValuesTextView.visibility = View.VISIBLE
                }
                else
                    noValuesTextView.visibility = View.GONE
            }
        }
    }

    private fun showDeleteAllConfirmationDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle("Deshironi ti fshini te gjitha rekordet?")
            .setMessage("Ky opsion do ti fshij te gjitha rekordet!")
            .setPositiveButton("Anulo") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setNegativeButton("Fshi"){ dialogInterface,_ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteAllTasks()
                }
                animateHasNoValuesTextView()
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun animateHasNoValuesTextView(){
        Preferences.hasValuesState(false)
        binding.noValuesTextView.alpha = 0f
        binding.noValuesTextView.visibility = View.VISIBLE
        binding.noValuesTextView.animate().apply {
            duration = 1250
            alpha(1f)
        }
    }
}