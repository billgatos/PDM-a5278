package com.example.lojasocialbd

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utilizadores")
data class Utilizador(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val numeroFornecedor: String,
    val tipo: String
)

@Entity(tableName = "voluntarios")
data class Voluntario(
    @PrimaryKey(autoGenerate = true) val idVoluntario: Long = 0,
    val nome: String
)

@Entity(tableName = "familias")
data class Familia(
    @PrimaryKey(autoGenerate = true) val idFamilia: Long = 0,
    val numeroFamilia: Int,
    val nome: String,
    val contato: String
)

@Entity(tableName = "visitas")
data class Visita(
    @PrimaryKey(autoGenerate = true) val idVisita: Long = 0,
    val numeroVisita: Int,
    val dataSaida: String,
    val dataRetorno: String,
    val voluntarioId: Long,  // Relacionamento com Voluntário
    val familiaId: Long,     // Relacionamento com Família
    val verificouLeis: Boolean
)
