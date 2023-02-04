package com.example.llogaritesirryms.UI

import android.R
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.llogaritesirryms.UI.viewmodels.ResultViewModel
import com.example.llogaritesirryms.databinding.ResultFragmentBinding


class ResultFragment : Fragment() {
    lateinit var binding: ResultFragmentBinding
    lateinit var resultViewModel: ResultViewModel
    private val safeArgs by navArgs<ResultFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        resultViewModel = ViewModelProvider(requireActivity())[ResultViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calcPackage = safeArgs.calcpackage

        binding.apply {
            with(calcPackage) {
                welcomeText.text = "Rezultatet e llogaritjes së: ${this?.userName}"
                a1ekaluar.setText(
                    calcPackage?.let {
                        calculateIndividualCategories(it.a1etashme, it.a1ekaluar, 1)
                    }
                )
                a2ekaluar.setText(
                    calcPackage?.let {
                        calculateIndividualCategories(it.a2etashme, it.a2ekaluar, 2)
                    }
                )
                a1etashme.text = calcPackage?.let {
                    calculateFinalPrice(it.a1etashme,it.a1ekaluar,it.a2etashme,it.a2ekaluar)
                }
                a2etashme.text = this?.a2etashme.toString()
            }
        }
    }
    fun calculateIndividualCategories(vleraETashme: Int, vleraEKaluar: Int, lloji : Int): SpannableString {
        when(lloji){
            1 -> {
                val price = "Vlera e hargjitur me te shtrenjten eshte : ${resultViewModel.calculateA1Spent(vleraETashme, vleraEKaluar)}€"
                val ss = SpannableString(price)
                ss.setSpan(StyleSpan(Typeface.BOLD), ss.length-7, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                return ss
            }
            2 -> {
                val price = "Vlera e hargjitur me te liren eshte : ${resultViewModel.calculateA2Spent(vleraETashme, vleraEKaluar)}€"
                val ss = SpannableString(price)
                ss.setSpan(StyleSpan(Typeface.BOLD), ss.length-7, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                return ss
            }
        }
        throw java.lang.IllegalArgumentException("Something went wrong")
    }

    fun calculateFinalPrice(a1ETashme: Int ,a1EKaluar: Int,a2ETashme: Int,a2EKaluar: Int) : SpannableString {
        val finalPrice = resultViewModel.calculateFinalPrice(a1ETashme,a1EKaluar,a2ETashme,a2EKaluar)
        val price = "Borgji total eshte : $finalPrice€"
        val ss = SpannableString(price)
        ss.setSpan(StyleSpan(Typeface.BOLD), ss.length-7, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }
}