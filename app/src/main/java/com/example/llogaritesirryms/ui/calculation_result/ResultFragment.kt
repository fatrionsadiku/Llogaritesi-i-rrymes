package com.example.llogaritesirryms.ui.calculation_result

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.llogaritesirryms.data.Preferences
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.databinding.ResultFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ResultFragment : Fragment() {
    lateinit var binding: ResultFragmentBinding
    private val resultViewModel: ResultViewModel by viewModels()
    private val safeArgs by navArgs<ResultFragmentArgs>()
    private var totalA1EShpenzuar : String = ""
    private var totalA2EShpenzuar : String = ""
    private var borgjiTotal : String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calcPackage = safeArgs.calcpackage

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.apply {
            with(calcPackage) {
                resultTitle.text = "Rezultatet e llogaritjes së: ${this?.userName}"
                vleraEHargjiturMeA1.setText(
                    calcPackage?.let {
                        calculateIndividualCategories(it.a1etashme, it.a1ekaluar, 1)
                    }
                )
                vleraEHargjiturMeA2.setText(
                    calcPackage?.let {
                        calculateIndividualCategories(it.a2etashme, it.a2ekaluar, 2)
                    }
                )
                borgjiTotalValue.text = calcPackage?.let {
                    calculateFinalPrice(it.a1etashme,it.a1ekaluar,it.a2etashme,it.a2ekaluar)
                }
                totalA1EShpenzuar = "${resultViewModel.calculateA1Spent(this!!.a1etashme, a1ekaluar)}€"
                totalA2EShpenzuar = "${resultViewModel.calculateA2Spent(a2etashme, a2ekaluar)}€"
                borgjiTotal = "${totalA1EShpenzuar.removeSuffix("€").toDouble() + totalA2EShpenzuar.removeSuffix("€").toDouble()}€"
            }
            dummyDataGenerator.setOnLongClickListener { 
                addDummyDataToDataBase()
                Toast.makeText(requireContext(),"Dummy data succesfully inserted", Toast.LENGTH_SHORT).show()
                true
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            resultViewModel.insertCalculatedPackage(CalcInfo(userName = calcPackage!!.userName, totalA1EShpenzuar = totalA1EShpenzuar, totalA2EShpenzuar = totalA2EShpenzuar, borgjiTotal = borgjiTotal))
            Preferences.hasValuesState(true)
        }


    }
    private fun calculateIndividualCategories(vleraETashme: Int, vleraEKaluar: Int, lloji : Int): SpannableString {
        when(lloji){
            1 -> {
                val price = "${resultViewModel.calculateA1Spent(vleraETashme, vleraEKaluar)}€"
                val ss = SpannableString(price)
                ss.setSpan(StyleSpan(Typeface.BOLD), 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                return ss
            }
            2 -> {
                val price = "${resultViewModel.calculateA2Spent(vleraETashme, vleraEKaluar)}€"
                val ss = SpannableString(price)
                ss.setSpan(StyleSpan(Typeface.BOLD), 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                return ss
            }
        }
        throw java.lang.IllegalArgumentException("Something went wrong")
    }

    private fun calculateFinalPrice(a1ETashme: Int, a1EKaluar: Int, a2ETashme: Int, a2EKaluar: Int) : SpannableString {
        val finalPrice = resultViewModel.calculateFinalPrice(a1ETashme,a1EKaluar,a2ETashme,a2EKaluar)
        val price = "$finalPrice€"
        val ss = SpannableString(price)
        ss.setSpan(StyleSpan(Typeface.BOLD), 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }

    private fun addDummyDataToDataBase(){
        viewLifecycleOwner.lifecycleScope.launch {
            for (i in 0..15){
                val name = Faker().name.firstName()
                val A1 = (10..100).random()
                val A2 = (10..100).random()
                val totali = A1 + A2
                resultViewModel.insertCalculatedPackage(
                    CalcInfo(
                        id = null,
                        userName = name,
                        totalA1EShpenzuar = "$A1€",
                        totalA2EShpenzuar =  "$A2€",
                        borgjiTotal = "$totali€",
                        created = 0
                    )
                )
            }
        }
    }
}