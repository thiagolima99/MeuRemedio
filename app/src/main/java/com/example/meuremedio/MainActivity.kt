package com.example.meuremedio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNomeMedicamento: EditText
    private lateinit var buttonProximo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNomeMedicamento = findViewById(R.id.camptexto)
        buttonProximo = findViewById(R.id.buttonprox)

        buttonProximo.setOnClickListener {
            val nomeMedicamento = editTextNomeMedicamento.text.toString().trim()

            if (nomeMedicamento.isNotEmpty()) {
                val intent = Intent(this, FrequenciaActivity::class.java)
                intent.putExtra("NOME_MEDICAMENTO", nomeMedicamento)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, insira o nome do medicamento.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}