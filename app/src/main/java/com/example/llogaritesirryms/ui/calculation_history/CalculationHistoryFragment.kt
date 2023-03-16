package com.example.llogaritesirryms.ui.calculation_history

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.llogaritesirryms.R
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.databinding.CalculationHistoryFragmentBinding
import com.example.llogaritesirryms.ui.CalcHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CalculationHistoryFragment() : Fragment() {
    lateinit var binding: CalculationHistoryFragmentBinding
    private val viewModel: CalcHistoryViewModel by viewModels()
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

        viewModel.calculations.observe(viewLifecycleOwner) {
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
                if (!Preferences.getValuesState()) {
                    noValuesTextView.visibility = View.VISIBLE
                } else
                    noValuesTextView.visibility = View.GONE
            }

            userSearch.apply {
                addTextChangedListener {
                    viewModel.searchQuery.value = it.toString()
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        this.clearFocus()
                    }
                    false
                }

            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val currentCalculatedRecord =
                        calcAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onRecordSwipe(currentCalculatedRecord)
                    Toast.makeText(
                        requireContext(),
                        "Rekordi u shlye me sukses",
                        Toast.LENGTH_SHORT
                    )
                        .apply {
                            setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
                        }
                        .show()
                    if (calcAdapter.currentList.size == 0) {
                        animateHasNoValuesTextView()
                    }
                }
            }).attachToRecyclerView(calcHistoryRecyclerView)
        }
    }

    private fun showDeleteAllConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Deshironi ti fshini te gjitha rekordet?")
            .setMessage("Ky opsion do ti fshij te gjitha rekordet!")
            .setPositiveButton("Anulo") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setNegativeButton("Fshi") { dialogInterface, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteAllTasks()
                }
                animateHasNoValuesTextView()
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun animateHasNoValuesTextView() {
        Preferences.hasValuesState(false)
        binding.noValuesTextView.alpha = 0f
        binding.noValuesTextView.visibility = View.VISIBLE
        binding.noValuesTextView.animate().apply {
            duration = 1250
            alpha(1f)
        }
    }
}