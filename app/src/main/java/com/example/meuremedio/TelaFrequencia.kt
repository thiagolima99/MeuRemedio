package com.example.meuremedio

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FrequenciaActivity : AppCompatActivity() {

    private lateinit var textViewNomeRecebido: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_frequencia) // Layout da FrequenciaActivity

        textViewNomeRecebido = findViewById(R.id.textViewNomeMedicamentoRecebido)

        val nomeMedicamento = intent.getStringExtra("NOME_MEDICAMENTO")

        if (nomeMedicamento != null) {
            textViewNomeRecebido.text = nomeMedicamento
        } else {
            textViewNomeRecebido.text = "Nome não informado"
        }

    }
}