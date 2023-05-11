package com.example.cookingcalculator

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityManagerCompat
import com.example.cookingcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_ounces -> (cost / 28.35)
            R.id.option_grams -> (cost * 28.35)
            R.id.option_fluid -> (cost / 29.57)
            else -> (cost * 29.57)
        }

        var tip = tipPercentage


        val formattedTip = NumberFormat.getInstance().format(tip)
        val measureType = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_ounces -> binding.tipResult.text =
                getString(R.string.tip_amount, formattedTip) + "oz"
            R.id.option_grams -> binding.tipResult.text =
                getString(R.string.tip_amount, formattedTip) + "g"
            R.id.option_fluid -> binding.tipResult.text =
                getString(R.string.tip_amount, formattedTip) + "fl oz"
            else -> binding.tipResult.text = getString(R.string.tip_amount, formattedTip) + "ml"
        }
    }
}