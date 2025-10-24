package com.regresionlineal.modelos

/**
 * Modelo que representa la solicitud para calcular una regresión lineal
 */
data class SolicitudRegresion(
    val puntos: List<PuntoDatos> // Lista de puntos (X, Y) enviados para el análisis
)
