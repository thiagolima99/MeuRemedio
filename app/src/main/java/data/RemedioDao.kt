package com.example.meuremedio.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface RemedioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignora se tentar inserir um com o mesmo ID (improvável com autoGenerate)
    suspend fun inserirRemedio(remedio: Remedio) // 'suspend' para usar com Coroutines

    @Update
    suspend fun atualizarRemedio(remedio: Remedio)

    @Delete
    suspend fun deletarRemedio(remedio: Remedio)

    @Query("SELECT * FROM remedios_table ORDER BY nome ASC")
    fun getAllRemedios(): LiveData<List<Remedio>> // Para observar mudanças na UI
    // Alternativamente, com Flow:
    // fun getAllRemediosFlow(): Flow<List<Remedio>>

    @Query("SELECT * FROM remedios_table WHERE id = :remedioId")
    suspend fun getRemedioById(remedioId: Int): Remedio?
}