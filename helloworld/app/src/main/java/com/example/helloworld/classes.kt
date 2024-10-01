package com.example.helloworld

// Classe Utilizador
data class Utilizador(
    val id: Int,
    val nome: String,
    val numeroFornecedor: String,
    val tipo: String // Exemplo: "ADM" ou outro tipo
)

// Classe Terrenato
data class Terrenato(
    val idTerrenato: Int,
    val descricao: String,
    val foto: String,
    val url: String,
    val tipo: String,
    val botaoSaida: Boolean,
    val idDivulgador: Int
)

// Classe PadraoEspecial
data class PadraoEspecial(
    val numeroCadastro: String,
    val numeroFamilia: Int,
    val descricao: String,
    val dadosRegras: String
)

// Classe Familia
data class Familia(
    val numeroFamilia: Int,
    val nome: String,
    val contato: String,
    val pais: Pais // Relacionamento com a classe Pais
)

// Classe Pessoa
data class Pessoa(
    val idPessoa: Int,
    val nome: String,
    val contato: String,
    val dataNascimento: String,
    val pais: Pais // Relacionamento com a classe Pais
)

// Classe Visita
data class Visita(
    val numeroVisita: Int,
    val dataSaida: String,
    val dataRetorno: String?,
    val familia: Familia, // Relacionamento com a Família
    val nome: String,
    val voluntarioSaida: Voluntario?, // Relacionamento com o Voluntário de saída
    val verificouLeis: Boolean
)

// Classe Voluntario
data class Voluntario(
    val idVoluntario: Int,
    val nome: String
)

// Classe Pais
data class Pais(
    val codigo: String,
    val nome: String
)

// Classe Agenda
data class Agenda(
    val idAgenda: Int,
    val nome: String,
    val data: String,
    val voluntario: Voluntario, // Relacionamento com Voluntário
    val familia: Familia // Relacionamento com Família
)
