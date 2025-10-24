package com.regresionlineal.controlador

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Controlador encargado de manejar las peticiones web (interfaz de usuario)
 */
@Controller
class ControladorWeb {

    // Ruta principal del sitio web ("/")
    @GetMapping("/")
    fun panelControl(): String {
        // Retorna la vista llamada "panel.html" desde templates/
        return "panel"
    }
}
