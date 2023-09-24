package com.cyrusthedev.ageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var txtSelectedDate : TextView
    lateinit var txtLived : TextView
    lateinit var txtageInHours : TextView
    lateinit var txtageInMin : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMain : Button = findViewById(R.id.btnMain)

        txtSelectedDate  = findViewById(R.id.txtSelectedDate)
        txtLived = findViewById(R.id.txt3)
        txtageInHours = findViewById(R.id.txtHours)
        txtageInMin = findViewById(R.id.txtMin)

        btnMain.setOnClickListener {
            dateOnClickListener()
        }


    }

    fun dateOnClickListener() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var selectedDate : String? = null

        val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year_, month_, dayOfMonth_ ->
                    selectedDate = "$year_/${month_+1}/$dayOfMonth_"

                    txtSelectedDate.setText(selectedDate)
                    txtLived.visibility = View.VISIBLE

                    val sdf = SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH)
                    val date = sdf.parse(selectedDate)
                    val dateInMin = date.time / 60000
                    val nowDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val nowInMin = nowDate.time / 60000
                    val ageInMin = nowInMin - dateInMin
                    val ageInHour = ageInMin / 60

                    txtageInHours.setText("${ageInHour.toString()} hours")
                    txtageInMin.setText("${ageInMin.toString()} minutes")
                },
                year, month, day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}

