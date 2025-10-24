package com.regresionlineal.servicio

import com.regresionlineal.modelos.PuntoDatos
import com.regresionlineal.modelos.ResultadoRegresion
import org.springframework.stereotype.Service
import kotlin.math.pow
import java.util.Locale

/**
 * Servicio para realizar el análisis de regresión lineal
 * Implementa el método de mínimos cuadrados ordinarios (OLS)
 */
@Service
class ServicioRegresionLineal {

    /**
     * Calcula la regresión lineal a partir de una lista de puntos (x, y)
     */
    fun calcularRegresion(puntos: List<PuntoDatos>): ResultadoRegresion {
        require(puntos.size >= 2) { "Se requieren al menos 2 puntos de datos" } // Validar cantidad mínima de puntos

        val n = puntos.size.toDouble() // Número total de puntos

        // --- Calcular sumatorias necesarias ---
        var sumaX = 0.0
        var sumaY = 0.0
        var sumaXY = 0.0
        var sumaX2 = 0.0

        // Recorrer los puntos y acumular valores
        for (punto in puntos) {
            sumaX += punto.x
            sumaY += punto.y
            sumaXY += punto.x * punto.y
            sumaX2 += punto.x * punto.x
        }

        // Validar que haya variabilidad en X (evita división por cero)
        val valoresXUnicos = puntos.map { it.x }.distinct()
        require(valoresXUnicos.size > 1) { "Los valores de X deben tener variabilidad" }

        // --- Calcular pendiente e intersección ---
        val numerador = (n * sumaXY) - (sumaX * sumaY)
        val denominador = (n * sumaX2) - (sumaX * sumaX)
        require(denominador != 0.0) { "Error: denominador cero en cálculo de pendiente" }

        val pendiente = numerador / denominador                    // m = pendiente
        val interseccion = (sumaY - pendiente * sumaX) / n          // b = intersección con el eje Y

        // --- Calcular coeficiente de determinación (R²) ---
        val mediaY = sumaY / n
        var sumaCuadradosResidual = 0.0  // Diferencia entre valores reales y predichos
        var sumaCuadradosTotal = 0.0     // Variación total de los valores Y

        for (punto in puntos) {
            val yPredicho = pendiente * punto.x + interseccion
            sumaCuadradosResidual += (punto.y - yPredicho).pow(2)
            sumaCuadradosTotal += (punto.y - mediaY).pow(2)
        }

        // R² mide qué tan bien se ajusta la recta a los datos
        val rCuadrado = if (sumaCuadradosTotal == 0.0) 1.0 else 1.0 - (sumaCuadradosResidual / sumaCuadradosTotal)

        // --- Información para depuración ---
        println(" ANÁLISIS COMPLETADO:")
        println("   Puntos analizados: ${puntos.size}")
        println("   Pendiente: $pendiente")
        println("   Intersección: $interseccion")
        println("   R²: $rCuadrado")

        // Generar la ecuación legible de la línea
        val ecuacion = construirEcuacion(pendiente, interseccion)

        // Devolver los resultados como objeto de datos
        return ResultadoRegresion(
            pendiente = pendiente,
            interseccion = interseccion,
            ecuacion = ecuacion,
            rCuadrado = rCuadrado,
            puntos = puntos
        )
    }

    /**
     * Construye la ecuación de la recta en formato estándar (y = mx + b)
     * Usa Locale.US para asegurar punto decimal (no coma)
     */
    private fun construirEcuacion(pendiente: Double, interseccion: Double): String {
        val pendienteStr = String.format(Locale.US, "%.4f", pendiente)             // Formatear pendiente con 4 decimales
        val interseccionStr = String.format(Locale.US, "%.4f", kotlin.math.abs(interseccion)) // Valor absoluto de la intersección
        val signo = if (interseccion >= 0) "+" else "-"                            // Determinar el signo correcto

        return "y = ${pendienteStr}x $signo $interseccionStr"                     // Ecuación completa en formato legible
    }
}
