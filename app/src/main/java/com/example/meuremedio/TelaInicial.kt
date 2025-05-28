package com.example.meuremedio
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaInicial : AppCompatActivity() {

    private lateinit var buttonComprimido: Button
    private lateinit var buttonGotas: Button
    private lateinit var buttonInalacao: Button
    private lateinit var buttonInjecao: Button

    companion object {
        const val TIPO_MEDICAMENTO = "TIPO_MEDICAMENTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_inicial) // Defina o layout UMA VEZ

        buttonComprimido = findViewById(R.id.buttoncomprimido)
        buttonGotas = findViewById(R.id.buttongotas)
        buttonInalacao = findViewById(R.id.buttoninalacao)
        buttonInjecao = findViewById(R.id.buttoninjecao)

        buttonComprimido.setOnClickListener {
            navegarParaProximaTela("Comprimido")
        }

        buttonGotas.setOnClickListener {
            navegarParaProximaTela("Gotas")
        }

        buttonInalacao.setOnClickListener {
            navegarParaProximaTela("Inalação")
        }

        buttonInjecao.setOnClickListener {
            navegarParaProximaTela("Injeção")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets -> // OU R.id.nomeapp
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun navegarParaProximaTela(tipoMedicamento: String) {
        val intent = Intent(this, FrequenciaActivity::class.java)
        intent.putExtra(TIPO_MEDICAMENTO, tipoMedicamento)
        startActivity(intent)
        // finish() // Opcional
    }
}