package com.example.imcapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.toColor
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class ImcResultActivity : AppCompatActivity() {
    private lateinit var resultadoNumero:TextView
    private lateinit var resultadoParteArriba:TextView
    private lateinit var resultadoParteAbajo:TextView
    private lateinit var botonCalcular:AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        val resultadoIMC: Double? = intent.extras?.getDouble("resultadoIMC")
        resultadoNumero.text = DecimalFormat("#.##").format(resultadoIMC)
        calcularComposicionCorporal(resultadoIMC)
    }

    private fun calcularComposicionCorporal(resultadoIMC: Double?) {
        if (resultadoIMC != null) {
            if (resultadoIMC < 18.5){
                resultadoParteArriba.text = getString(R.string.infrapeso)
                resultadoParteArriba.setTextColor(getColor(R.color.infrapeso))
                resultadoParteAbajo.text = getString(R.string.textoinfrapeso)
            }else if(resultadoIMC >= 18.5 && resultadoIMC <25 ){
                resultadoParteArriba.text = getString(R.string.normal)
                resultadoParteArriba.setTextColor(getColor(R.color.normal))
                resultadoParteAbajo.text =getString(R.string.textonormal)
            }else if(resultadoIMC >= 25 && resultadoIMC <30 ){
                resultadoParteArriba.text = getString(R.string.sobrepeso)
                resultadoParteArriba.setTextColor(getColor(R.color.sobrepeso))
                resultadoParteAbajo.text =getString(R.string.textosobrepeso)
            }else if(resultadoIMC >= 30){
                resultadoParteArriba.text = getString(R.string.obesidad)
                resultadoParteArriba.setTextColor(getColor(R.color.obesidad))
                resultadoParteAbajo.text =getString(R.string.textoobesidad)
            }
        }
    }
    private fun initListeners() {
        botonCalcular.setOnClickListener{
            val intent = Intent(this, Imccalculatormain::class.java)
            startActivity(intent)
        }

    }
    private fun initComponents() {
        resultadoNumero = findViewById(R.id.resultadoNumero)
        resultadoParteArriba = findViewById(R.id.resultadoParteArriba)
        resultadoParteAbajo = findViewById(R.id.resultadoParteAbajo)
        botonCalcular = findViewById(R.id.botonCalcular)
    }

}