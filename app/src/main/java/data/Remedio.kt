package com.example.meuremedio.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remedios_table")
data class Remedio(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Chave primária autoincrementável
    val nome: String,
    val tipo: String,
    val horarioHoras: Int,     // Ex: 14 (para 14:30)
    val horarioMinutos: Int,   // Ex: 30 (para 14:30)
    val frequenciaDescricao: String // Ex: "A cada 8 horas", "Diariamente"
    // Adicione outros campos conforme necessário (ex: dosagem, data de início, etc.)
)