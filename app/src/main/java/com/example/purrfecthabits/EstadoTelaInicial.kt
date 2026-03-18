package com.example.purrfecthabits

data class EstadoTelaInicial(
    val habitosConcluidos: Int = 3,
    val totalHabitos: Int = 5,
    val gatoEstaFeliz: Boolean = true
) {
    val porcentagemProgresso: Float
        get() = if (totalHabitos > 0) habitosConcluidos.toFloat() / totalHabitos else 0f
}