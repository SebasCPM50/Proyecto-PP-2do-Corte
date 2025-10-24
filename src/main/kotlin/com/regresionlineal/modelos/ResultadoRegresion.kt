package com.regresionlineal.modelos

/**
 * Representa el resultado del análisis de regresión lineal
 */
data class ResultadoRegresion(
    val pendiente: Double,      // Pendiente de la línea de regresión (m)
    val interseccion: Double,   // Intersección con el eje Y (b)
    val ecuacion: String,       // Ecuación generada en formato legible
    val rCuadrado: Double,      // Coeficiente de determinación R² (precisión del modelo)
    val puntos: List<PuntoDatos> // Lista de puntos de datos originales usados
)

