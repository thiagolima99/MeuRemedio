package com.example.meuremedio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNomeMedicamento: EditText
    private lateinit var buttonProximo: Button
    private lateinit var textViewTipoSelecionado: TextView

    private var tipoMedicamentoRecebido: String? = null

    companion object {
        const val NOME_MEDICAMENTO = "NOME_MEDICAMENTO"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNomeMedicamento = findViewById(R.id.camptexto)
        buttonProximo = findViewById(R.id.buttonprox)


        // Receber o tipo de medicamento da TelaInicial
        tipoMedicamentoRecebido = intent.getStringExtra(TelaInicial.TIPO_MEDICAMENTO)

        if (tipoMedicamentoRecebido != null) {
            textViewTipoSelecionado.text = "Tipo:$tipoMedicamentoRecebido"
        } else {
            textViewTipoSelecionado.text = "Tipo não especificado"
        }

        buttonProximo.setOnClickListener {
            val nomeMedicamento = editTextNomeMedicamento.text.toString().trim()

            if (nomeMedicamento.isNotEmpty()) {
                val intent = Intent(this, FrequenciaActivity::class.java)
                intent.putExtra(NOME_MEDICAMENTO, nomeMedicamento)
                if (tipoMedicamentoRecebido != null) {
                    intent.putExtra(TelaInicial.TIPO_MEDICAMENTO, tipoMedicamentoRecebido)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, insira o nome do medicamento.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}