package com.regresionlineal.controlador
// Paquete donde se ubica el controlador principal del análisis de regresión

import com.regresionlineal.modelos.SolicitudRegresion
import com.regresionlineal.modelos.ResultadoRegresion
import com.regresionlineal.servicio.ServicioRegresionLineal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
// Importaciones necesarias para manejar solicitudes HTTP y usar el servicio de regresión

/**
 * API REST para análisis de regresión lineal
 */
@RestController // Indica que esta clase manejará peticiones REST y devolverá datos JSON
@RequestMapping("/api/analisis") // Ruta base del controlador
@CrossOrigin(origins = ["*"]) // Permite peticiones desde cualquier dominio (evita problemas de CORS)
class ControladorRegresion(
    private val servicioAnalisis: ServicioRegresionLineal // Inyección del servicio que realiza el cálculo
) {

    @PostMapping("/regresion") // Endpoint para ejecutar el análisis de regresión
    fun realizarRegresion(@RequestBody solicitud: SolicitudRegresion): ResponseEntity<Any> {
        return try {
            // Valida que haya puntos de datos
            if (solicitud.puntos.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(mapOf("error" to "Se requieren datos para el análisis"))
            }

            // Llama al servicio para calcular pendiente, intersección, ecuación y R²
            val resultado = servicioAnalisis.calcularRegresion(solicitud.puntos)

            // Muestra resultados en consola (para depuración)
            println(" Análisis completado exitosamente")
            println("   Ecuación: ${resultado.ecuacion}")
            println("   R²: ${resultado.rCuadrado}")

            // Devuelve resultado al frontend (HTTP 200)
            ResponseEntity.ok(resultado)

        } catch (e: IllegalArgumentException) {
            // Error de validación (datos incorrectos)
            println(" Error de validación: ${e.message}")
            ResponseEntity.badRequest()
                .body(mapOf("error" to e.message))

        } catch (e: Exception) {
            // Error interno del servidor
            println(" Error interno: ${e.message}")
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "Error en el análisis: ${e.message}"))
        }
    }

    @GetMapping("/estado") // Endpoint para verificar que el servicio está activo
    fun obtenerEstado(): ResponseEntity<Map<String, String>> {
        // Devuelve información básica del estado del API
        return ResponseEntity.ok(mapOf(
            "estado" to "EN LÍNEA",
            "servicio" to "Panel de Análisis de Regresión",
            "version" to "2.0.0"
        ))
    }
}

