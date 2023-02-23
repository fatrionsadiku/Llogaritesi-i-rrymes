package com.example.llogaritesirryms.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.databinding.CalculationHistoryLayoutBinding

class CalcHistoryAdapter : ListAdapter<CalcInfo, CalcHistoryAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(private val binding : CalculationHistoryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(calcResult : CalcInfo){
            binding.apply {
                a1EShpenzuar.text = calcResult.totalA1EShpenzuar
                a2EShpenzuar.text = calcResult.totalA2EShpenzuar
                useri.text = calcResult.userName
                totali.text = calcResult.borgjiTotal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CalculationHistoryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}

class DiffCallBack : DiffUtil.ItemCallback<CalcInfo>(){
    override fun areItemsTheSame(oldItem: CalcInfo, newItem: CalcInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CalcInfo, newItem: CalcInfo): Boolean {
        return oldItem == newItem
    }
}