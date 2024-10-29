package com.example.imcapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class Imccalculatormain : AppCompatActivity() {
    private var resultado:Double=0.00
    private var isMaleSelected:Boolean = false
    private var weight = 60
    private var age = 26
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var numPeso:TextView
    private lateinit var addPeso:FloatingActionButton
    private lateinit var removePeso:FloatingActionButton
    private lateinit var numEdad:TextView
    private lateinit var addEdad:FloatingActionButton
    private lateinit var removeEdad:FloatingActionButton
    private lateinit var botonCalcular:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imccalculatormain)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI() {
        setGenderColor()
        setWeight(true)
    }

    private fun initListeners() {

        viewMale.setOnClickListener {
            isMaleSelected=true
            setGenderColor()}
        viewFemale.setOnClickListener {
            isMaleSelected=false
            setGenderColor()}
        rsHeight.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }
        addPeso.setOnClickListener{
            setWeight(true)
        }
        removePeso.setOnClickListener{
            setWeight(false)
        }
        addEdad.setOnClickListener{
            setage(true)
        }
        removeEdad.setOnClickListener{
            setWeight(false)
        }
        botonCalcular.setOnClickListener{
            calculateIMC()
            navigate2result(resultado)
        }


    }

    private fun navigate2result(resultado:Double) {

    }

    private fun calculateIMC(): Double {
        val alturaEnMetros = tvHeight.text.toString().replace(" cm", "").toDouble() / 100
        resultado = weight/(alturaEnMetros*alturaEnMetros)
        return resultado
    }

    private fun setage(boolean: Boolean) {
        if (boolean){
            age++
        } else if (age>0){
            age--
        }
        numEdad.text="$age"
    }

    private fun setWeight(boolean: Boolean) {
        if (boolean){
            weight++
        } else if (weight>0){
            weight--
        }
        numPeso.text="$weight"
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun getBackgroundColor(isComponentSelected: Boolean):Int{
        val colorReference = if(isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this,colorReference)
    }


    private fun initComponents() {
        viewFemale = findViewById(R.id.viewFemale)
        viewMale = findViewById(R.id.viewMale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        numPeso = findViewById(R.id.numPeso)
        addPeso = findViewById(R.id.addPeso)
        removePeso = findViewById(R.id.removePeso)
        numEdad = findViewById(R.id.numEdad)
        addEdad = findViewById(R.id.addEdad)
        removeEdad = findViewById(R.id.removeEdad)
        botonCalcular = findViewById(R.id.botonCalcular)
    }
}