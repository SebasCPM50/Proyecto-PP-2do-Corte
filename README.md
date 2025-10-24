# Panel de Análisis de Regresión Lineal

## Plataforma Avanzada de Análisis Estadístico

Aplicación web para análisis de regresión lineal con interfaz moderna tipo dashboard. Construida con **Spring Boot** y **Kotlin**.

---

## Características

- **Diseño Moderno de Dashboard** - Distribución profesional tipo grilla
- **Visualización Interactiva de Datos** - Integración avanzada con Chart.js
- **Análisis Estadístico** - Regresión por mínimos cuadrados ordinarios
- **Interfaz Responsive** - Funciona en todos los dispositivos
- **UI con Gradientes** - Diseño visual hermoso
- **Resultados en Tiempo Real** - Cálculo y visualización instantáneos
- **Completamente en Español** - Variables, interfaz y mensajes

---

## Inicio Rápido

### Prerrequisitos
- JDK 17 o superior
- IntelliJ IDEA (recomendado)

### Ejecutar la Aplicación

1. **Abrir en IntelliJ IDEA:**
   - Archivo → Abrir
   - Seleccionar la carpeta del proyecto
   - Esperar la sincronización de Gradle

2. **Ejecutar la aplicación:**
   - Abrir `Aplicacion.kt`
   - Hacer clic en el botón de play verde
   - O ejecutar: `./gradlew bootRun`

3. **Acceder al panel:**
   - Abrir navegador: http://localhost:8080
   - ¡Comenzar a agregar puntos de datos y analizar!

### Si es en Terminal

1. **Ir a la carpeta del proyecto**
   - Descargar el ZIP del Proyecto en GitHub
   - cd Proyecto-PP-2do-Corte-master

3. **Dar permisos (solo si está en Mac/Linux)**
   - chmod +x gradlew

4. **Limpiar y compilar el proyecto**
   - En Windows (PowerShell):
   - .\gradlew.bat clean build

   - En macOS / Linux:
   -  ./gradlew clean build

5. **Ejecutar**
   - En Windows:
   - $env:PORT="8080"; .\gradlew.bat bootRun

   - En macOS / Linux:
   - PORT=8080 ./gradlew bootRun

6. **LocalHost**
   - Abrir en el navegador en la siguiente dirección
   - http://localhost:8080
   - Ctrl + C para detener la ejecución
---

## Cómo Usar

1. **Agregar Puntos de Datos:**
   - Ingresar coordenadas X e Y
   - Hacer clic en "Agregar" o presionar Enter
   - Ver los puntos en la tabla de datos

2. **Cargar Datos de Ejemplo:**
   - Hacer clic en "Datos de Ejemplo" para demostración
   - Conjunto de datos precargado con buena correlación

3. **Ejecutar Análisis:**
   - Hacer clic en "Ejecutar Análisis" (requiere 2+ puntos)
   - Ver ecuación de regresión y estadísticas
   - Examinar la visualización interactiva

4. **Interpretar Resultados:**
   - **Pendiente (m)**: Tasa de cambio de Y por unidad de X
   - **Intersección Y (b)**: Valor de Y cuando X = 0
   - **Coeficiente R²**: Bondad de ajuste (escala 0-1)

---

## Arquitectura

```
src/main/kotlin/com/regresionlineal/
├── Aplicacion.kt                      # Clase principal de la aplicación
├── controlador/
│   ├── ControladorRegresion.kt        # Endpoints de la API REST
│   └── ControladorWeb.kt              # Controlador de página web
├── modelos/
│   ├── PuntoDatos.kt                  # Modelo de punto de datos
│   ├── SolicitudRegresion.kt          # Modelo de solicitud de API
│   └── ResultadoRegresion.kt          # Modelo de respuesta de API
└── servicio/
    └── ServicioRegresionLineal.kt     # Cálculos estadísticos
```

---

## Métodos Estadísticos

### Fórmula de Regresión Lineal

**Pendiente:** `m = (N∑(xy) - ∑x∑y) / (N∑(x²) - (∑x)²)`

**Intersección:** `b = (∑y - m∑x) / N`

**Coeficiente R²:** `R² = 1 - (SC_res / SC_tot)`

Donde:
- `N` = número de puntos de datos
- `SC_res` = suma de cuadrados residuales
- `SC_tot` = suma total de cuadrados

---

## Características de Diseño

- **Efectos Glassmorphism** - Tarjetas translúcidas
- **Fondos con Gradientes** - color llamativo
- **Elementos Interactivos** - efectos hover y animaciones
- **Tipografía Profesional** - Familia de fuentes Inter
- **Grilla Responsive** - Se adapta a todos los tamaños de pantalla

---

## Tecnologías

- **Backend:** Spring Boot 3.2.1, Kotlin 1.9.21
- **Frontend:** HTML5, CSS3, JavaScript ES6+
- **Visualización:** Chart.js 4.4.0
- **Iconos:** Font Awesome 6.0.0
- **Build:** Gradle con Kotlin DSL

---

## Endpoints de la API

### Análisis Estadístico
- **POST** `/api/analisis/regresion`
  - Solicitud: `{"puntos": [{"x": 1, "y": 2}, ...]}`
  - Respuesta: `{"pendiente": 1.5, "interseccion": 0.5, "rCuadrado": 0.95, ...}`

### Verificación de Estado
- **GET** `/api/analisis/estado`
  - Respuesta: `{"estado": "EN LÍNEA", "servicio": "Panel de Análisis de Regresión"}`

---

## Diseño Responsive

- **Escritorio:** Distribución completa de grilla del dashboard
- **Tablet:** Distribución de tarjetas apiladas
- **Móvil:** Columna única, entradas optimizadas

---

## Configuración

Editar `application.properties`:

```properties
server.port=8080
spring.application.name=Panel de Análisis de Regresión
logging.level.com.regresionlineal=DEBUG
```

---

## Despliegue

### Desarrollo
```bash
./gradlew bootRun
```

### Build de Producción
```bash
./gradlew build
java -jar build/libs/calculadora-regresion-lineal-2.0.0.jar
```

---

## Consejos de Uso

- **Calidad de Datos:** Asegurar valores X variados para resultados significativos
- **Tamaño de Muestra:** Más puntos de datos generalmente mejoran la precisión
- **Valores Atípicos:** Considerar limpieza de datos para mejores ajustes
- **Interpretación R²:** >0.7 indica correlación fuerte

---

## Uso Educativo

Perfecto para:
- Cursos de estadística
- Demostraciones de ciencia de datos
- Proyecos de investigación
- Capacitación en análisis de negocios

---

## Correcciones Implementadas

### Fix del Problema R²
- Uso de `Locale.US` en formateo de números
- Garantiza punto decimal (.) en lugar de coma (,)
- Compatible con formato JSON internacional
- Previene errores de parseo en el frontend

### Traducción Completa al Español
- Todas las variables en español
- Interfaz completamente traducida
- Mensajes y alertas en español
- Comentarios de código en español
