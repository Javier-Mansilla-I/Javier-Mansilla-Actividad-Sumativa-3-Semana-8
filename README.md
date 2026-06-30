# LlanquihueTourApp V2.0

## Nombre del proyecto
LlanquihueTourApp V2.0

## Breve descripción del sistema desarrollado
Sistema de gestión y administración de turismo para la región de Llanquihue desarrollado en Java. Permite cargar y validar de forma robusta la información de personas (empleados, operadores, proveedores) desde archivos planos externos, controlando excepciones de formato, correos válidos y unicidad de RUTs. Adicionalmente, despliega un menú de consola interactivo para realizar búsquedas y filtros dinámicos.

## Objetivo de la Semana
El objetivo de esta semana es **"Creando jerarquías de clases con herencia simple"**. Se implementó una jerarquía de clases para modelar distintos servicios turísticos que ofrece la empresa, permitiendo la reutilización de atributos comunes (como nombre y duración) mediante una clase base, la especialización en subclases utilizando constructores con `super(...)`, la sobrescritura del método `toString()`, y la organización lógica del proyecto en paquetes por responsabilidades (`ui`, `model`, `data`, `util`).

## Clases Creadas e Implementadas

### Paquete `model/` (Modelo de Datos)
* **`ServicioTuristico`** (Superclase): Clase base con los atributos comunes `nombre` (String) y `duracionHoras` (int). Sobrescribe `toString()`.
* **`RutaGastronomica`** (Subclase): Extiende a `ServicioTuristico`. Añade el atributo específico `numeroDeParadas` (int). Sobrescribe `toString()`.
* **`PaseoLacustre`** (Subclase): Extiende a `ServicioTuristico`. Añade el atributo específico `tipoEmbarcacion` (String). Sobrescribe `toString()`.
* **`ExcursionCultural`** (Subclase): Extiende a `ServicioTuristico`. Añade el atributo específico `lugarHistorico` (String). Sobrescribe `toString()`.
* *(Existentes de la semana anterior: `Persona`, `Empleado`, `Operador`, `Proveedor` y `Direccion`)*.

### Paquete `data/` (Persistencia y Datos de Prueba)
* **`GestorServicios`** (Nueva clase): Encargada de instanciar casos de prueba de los servicios turísticos, creando al menos dos objetos de cada subclase (`RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`) en el método `obtenerServiciosDePrueba()`.
* *(Existente de la semana anterior: `GestorDatos`, encargado de leer y validar las personas desde `personas.txt`)*.

### Paquete `ui/` (Interfaz de Usuario)
* **`Main`** (Punto de entrada): Llama a `GestorServicios.obtenerServiciosDePrueba()` y muestra los objetos resultantes por consola antes de dar paso al menú interactivo principal.

### Paquete `util/` (Utilidades)
* *(Existente de la semana anterior: `ValidadorRut`, encargado de la validación del RUT chileno)*.

## Instrucciones para ejecutar Main

Para ejecutar la aplicación principal, utiliza el script automatizado provisto en la raíz del proyecto.

### Requisitos previos:
* Tener instalado el **JDK 17 o superior** (el script busca automáticamente la versión activa en el sistema o bajo `C:\Program Files\Java`).
* Ejecutar en una consola de comandos con soporte para PowerShell.

### Pasos para compilar y ejecutar:
1. Abre una consola de PowerShell en la carpeta raíz del proyecto.
2. Ejecuta el siguiente comando para compilar todas las clases (empaquetando el modelo en una librería JAR en `dist/lib/`) y lanzar la clase `ui.Main`:
   ```powershell
   ./build_and_run.ps1
   ```
3. Al iniciar, el programa listará automáticamente en consola los servicios turísticos de prueba creados y luego abrirá el menú interactivo.
