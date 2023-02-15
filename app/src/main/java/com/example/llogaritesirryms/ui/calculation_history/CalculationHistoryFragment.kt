package com.example.llogaritesirryms.ui.calculation_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.databinding.CalculationHistoryFragmentBinding
import com.example.llogaritesirryms.ui.CalcHistoryAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculationHistoryFragment : Fragment() {
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

        val calcAdapter = CalcHistoryAdapter()

        viewModel.calculations.observe(viewLifecycleOwner){
            calcAdapter.submitList(it)
        }
        binding.apply {
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
}