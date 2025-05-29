package com.example.meuremedio

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.meuremedio.data.AppDatabase
import com.example.meuremedio.data.Remedio
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FrequenciaActivity : AppCompatActivity() {

    private lateinit var textViewNomeMedicamentoInfo: TextView
    private lateinit var textViewTipoMedicamentoInfo: TextView
    private lateinit var buttonDefinirHorario: Button
    private lateinit var textViewHorarioSelecionado: TextView
    private lateinit var editTextFrequencia: EditText
    private lateinit var buttonSalvarRemedio: Button
    private var nomeMedicamento: String? = null
    private var tipoMedicamento: String? = null
    private var horaSelecionada: Int = -1
    private var minutoSelecionado: Int = -1


    private lateinit var remedioDao: com.example.meuremedio.data.RemedioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_frequencia)

        remedioDao = AppDatabase.getDatabase(applicationContext).remedioDao()

        textViewNomeMedicamentoInfo = findViewById(R.id.textViewNomeMedicamentoInfo)
        textViewTipoMedicamentoInfo = findViewById(R.id.textViewTipoMedicamentoInfo)
        buttonDefinirHorario = findViewById(R.id.buttonDefinirHorario)
        textViewHorarioSelecionado = findViewById(R.id.textViewHorarioSelecionado)
        editTextFrequencia = findViewById(R.id.editTextFrequencia)
        buttonSalvarRemedio = findViewById(R.id.buttonSalvarRemedio)

        nomeMedicamento = intent.getStringExtra(MainActivity.NOME_MEDICAMENTO)
        tipoMedicamento = intent.getStringExtra(TelaInicial.TIPO_MEDICAMENTO)

        textViewNomeMedicamentoInfo.text = "Medicamento: ${nomeMedicamento ?: "N/A"}"
        textViewTipoMedicamentoInfo.text = "Tipo: ${tipoMedicamento ?: "N/A"}"

        buttonDefinirHorario.setOnClickListener {
            abrirTimePickerDialog()
        }

        buttonSalvarRemedio.setOnClickListener {
            salvarInformacoesDoRemedio()
        }
    }

    private fun abrirTimePickerDialog() {
        val calendario = Calendar.getInstance()
        val horaAtual = if (horaSelecionada != -1) horaSelecionada else calendario.get(Calendar.HOUR_OF_DAY)
        val minutoAtual = if (minutoSelecionado != -1) minutoSelecionado else calendario.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            // Listener que é chamado quando o usuário define um horário
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                horaSelecionada = hourOfDay
                minutoSelecionado = minute
                atualizarTextViewHorario(hourOfDay, minute)
            },
            horaAtual,
            minutoAtual,
            true
        )
        timePickerDialog.show()
    }

    private fun atualizarTextViewHorario(hora: Int, minuto: Int) {

        val horaFormatada = String.format(Locale.getDefault(), "%02d", hora)
        val minutoFormatado = String.format(Locale.getDefault(), "%02d", minuto)
        textViewHorarioSelecionado.text = "Horário: $horaFormatada:$minutoFormatado"
    }

    private fun salvarInformacoesDoRemedio() {
        val nome = nomeMedicamento
        val tipo = tipoMedicamento
        val frequencia = editTextFrequencia.text.toString().trim()

        // Validações
        if (nome.isNullOrEmpty()) {
            Toast.makeText(this, "Nome do medicamento não pode ser vazio.", Toast.LENGTH_SHORT).show()
            return
        }
        if (tipo.isNullOrEmpty()) {
            Toast.makeText(this, "Tipo do medicamento não pode ser vazio.", Toast.LENGTH_SHORT).show()
            return
        }
        if (horaSelecionada == -1 || minutoSelecionado == -1) {
            Toast.makeText(this, "Por favor, defina o horário.", Toast.LENGTH_SHORT).show()
            return
        }
        if (frequencia.isEmpty()) {
            editTextFrequencia.error = "Frequência é obrigatória"
            Toast.makeText(this, "Por favor, defina a frequência.", Toast.LENGTH_SHORT).show()
            return
        }

        val novoRemedio = Remedio(
            nome = nome,
            tipo = tipo,
            horarioHoras = horaSelecionada,
            horarioMinutos = minutoSelecionado,
            frequenciaDescricao = frequencia
        )

        lifecycleScope.launch {
            remedioDao.inserirRemedio(novoRemedio)
            Toast.makeText(
                this@FrequenciaActivity,
                "Remédio '${novoRemedio.nome}' salvo!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}