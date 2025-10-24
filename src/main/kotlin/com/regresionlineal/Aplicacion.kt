package com.regresionlineal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Clase principal de la aplicación de regresión lineal
 * Marca el punto de entrada del proyecto Spring Boot
 */
@SpringBootApplication // Anotación que habilita la configuración automática y el escaneo de componentes
class AplicacionRegresionLineal

fun main(args: Array<String>) {
    // Función principal: inicia la aplicación Spring Boot
    runApplication<AplicacionRegresionLineal>(*args)
}
