package cosade.weinol.gabloin.white.ui.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import cosade.weinol.gabloin.R
import cosade.weinol.gabloin.databinding.ActivityHomeBinding
import cosade.weinol.gabloin.white.utils.DatePickerFragment
import java.util.*
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity(R.layout.activity_home), DatePickerDialog.OnDateSetListener {

    private val binding by viewBinding(ActivityHomeBinding::bind)
    private var date1Flag = false
    private var date2Flag = false
    private var date3Flag = false
    private var grossPension = 0.0
    private var gradutyPension = 0.0
    private var commutedPension = 0.0
    private var netPension = 0.0
    private var month = 0
    private var day: Int = 0
    private var year: Int = 0
    private var totalAgeVal: Long = 0
    private var totalServiceVal: Long = 0
    private var ageRateVal = 0.0
    private var currentBtn = 0
    private var lastBasicPay = 0
    private lateinit var c1: Calendar
    private lateinit var c2: Calendar
    private lateinit var c3: Calendar
    private lateinit var currentDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun initialize() {
        c1 = Calendar.getInstance()
        c2 = Calendar.getInstance()
        c3 = Calendar.getInstance()
        currentDate = Calendar.getInstance()
    }

    private fun setupListeners() {
        binding.btn1.setOnClickListener(View.OnClickListener {
            currentBtn = 1
            val datePicker: DialogFragment = DatePickerFragment()
            this.let { it1 -> datePicker.show(it1.supportFragmentManager, "date picker") }
        })

        binding.btn2.setOnClickListener(View.OnClickListener {
            currentBtn = 2
            val datePicker: DialogFragment = DatePickerFragment()
            this.let { it1 -> datePicker.show(it1.supportFragmentManager, "date picker") }
        })

        binding.btn3.setOnClickListener(View.OnClickListener {
            currentBtn = 3
            val datePicker: DialogFragment = DatePickerFragment()
            this.let { it1 -> datePicker.show(it1.supportFragmentManager, "date picker") }
        })

        binding.calculateBtn.setOnClickListener {
            if (isValidData()) {
                totalAgeVal = (currentDate.get(Calendar.YEAR) - c1.get(Calendar.YEAR)).toLong()
                totalServiceVal = (c3.get(Calendar.YEAR) - c2.get(Calendar.YEAR)).toLong()
                binding.totalAge.text = totalAgeVal.toString()
                binding.totalService.text = totalServiceVal.toString()
                ageRateVal = findAgeRate(totalAgeVal.toInt())
                lastBasicPay = binding.lastSalary.text.toString().toInt()
                grossPension = (totalServiceVal * lastBasicPay * 7 / 300.0).roundToInt().toDouble()
                gradutyPension = (grossPension * 0.35).roundToInt().toDouble()
                commutedPension = (gradutyPension * 12 * ageRateVal).roundToInt().toDouble()
                netPension = (grossPension * 0.65).roundToInt().toDouble()
                binding.grossPension.text = grossPension.toString()
                binding.gradutyPension.text = gradutyPension.toString()
                binding.commutedPension.text = commutedPension.toString()
                binding.netPension.text = netPension.toString()
            }
        }
    }

    private fun findAgeRate(age: Int): Double {
        val ageRateArray = doubleArrayOf(
            40.5043,
            39.7341,
            38.9653,
            38.1974,
            37.4307,
            36.6651,
            35.9006,
            35.1372,
            34.375,
            33.6143,
            32.8071,
            32.0974,
            31.3412,
            30.5869,
            29.8343,
            29.0841,
            28.3362,
            27.5908,
            26.8482,
            26.1009,
            25.3728,
            24.6406,
            23.9126,
            23.184,
            22.4713,
            21.7592,
            21.0538,
            20.3555,
            19.6653,
            18.9841,
            18.3129,
            17.6526,
            17.005,
            16.371,
            15.7517,
            15.1478,
            14.5602,
            13.9888,
            13.434,
            12.8953,
            12.3719
        )
        var x = 0.0
        if (age < 20) {
            x = 40.503
        } else if (age in 20..60) {
            x = ageRateArray[age - 20]
        }
        return x
    }

    private fun isValidData(): Boolean {
        if (!(!(!date1Flag || date2Flag) || date3Flag)) {
            return false
        }
        val days1: Long = c1.time.time
        val days2: Long = c2.time.time
        val days3: Long = c3.time.time

        return true
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        var month = month
        month++
        val currentDateString =
            "$day/$month/$year"
        when (currentBtn) {
            1 -> {
                c1[Calendar.YEAR] = year
                c1[Calendar.MONTH] = month
                c1[Calendar.DAY_OF_MONTH] = day
                binding.btn1.text = currentDateString
                date1Flag = true
            }
            2 -> {
                c2[Calendar.YEAR] = year
                c2[Calendar.MONTH] = month
                c2[Calendar.DAY_OF_MONTH] = day
                binding.btn2.text = currentDateString
                date2Flag = true
            }
            3 -> {
                c3[Calendar.YEAR] = year
                c3[Calendar.MONTH] = month
                c3[Calendar.DAY_OF_MONTH] = day
                binding.btn3.text = currentDateString
                date3Flag = true
            }
        }
    }
}