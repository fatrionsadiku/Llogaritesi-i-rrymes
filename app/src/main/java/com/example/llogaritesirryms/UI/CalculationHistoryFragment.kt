package com.example.llogaritesirryms.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.llogaritesirryms.UI.viewmodels.CalcHistoryViewModel
import com.example.llogaritesirryms.databinding.CalculationHistoryFragmentBinding
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

        binding.apply {
            calcHistoryRecyclerView.apply {
                adapter = calcAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.calculations.observe(viewLifecycleOwner){
            calcAdapter.submitList(it)
        }
    }
}