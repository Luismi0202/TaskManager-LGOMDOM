Para esta actividad, elegiré el servicio `ActividadService` de tu proyecto. Este servicio gestiona diversas operaciones relacionadas con actividades, como agregar subtareas, cambiar el estado de tareas y filtrar actividades. A continuación, estructuraré los pasos para cumplir con los objetivos de la actividad.

### Plan para Diseñar y Ejecutar Pruebas Unitarias para `ActividadService`
1. **Selección del Servicio**
   - Servicio elegido: `ActividadService`. (Hemos elegido este porque es el más completo además de que al final el programa va de gestionar esas actividades así que que mejor que usar el servicio de esas actividades)

2. **Identificación de Métodos Públicos**
Los métodos públicos que están en el servicio ActividadService (que es el que gestiona todo los métodos relacionados con las clases Tarea y Eventos) son...
     - `gestionarPrograma()`
     - `cambiarEstado(tarea: Tarea)`
     - `agregarSubtarea()`
     - `usuariosConActividades()`
     - `filtrarPorEstado()`
     - `filtradoPorEtiquetas()`
     - `filtradoPorUsuarios()`

3. **Diseño de Casos de Prueba**

En este apartado, teníamos que incluir una tabla con los distintos casos de pruebas de cada método y como se supone que deberían de actuar (esto se tiene en cuenta para que si da algún fallo, el programador sepa que debería de hacer esa función y entonces la podría identificar en la tabla y saber que se supone que debía de hacer ese método en lugar de dar un fallo. Sirve bastante para poder entender poco a poco lo que hace cada método ya que como realmente antes estábamos trabajando en grupo, pues me ha servido bastante para ir mirando las lógicas que hemos ido teniendo ya que no todos los métodos los he hecho yo solo, si no que nos hemos ido comunicando. En la tabla, se ven los métodos, con el caso de prueba (que es con el que se prueba el método para ver si funciona), el estado inicial del mock (que es una forma de simular el comportamiento de un objeto para hacerle como una verificación doble y ver que funciona de manera correcta), a acción que hace ese métodos y el resultado que se espera al ejecutar el método.

| Método                  | Caso de prueba                            | Estado inicial del mock                          | Acción                                      | Resultado esperado                                                                                                                                                   |
|-------------------------|-------------------------------------------|------------------------------------------------|--------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `cambiarEstado(...)`    | Cambiar estado a tarea existente          | Mock repo devuelve tarea válida                | Llamar `cambiarEstado(tarea)`              | Estado de la tarea y subtareas actualizado; interacción con mock para guardar cambios.                                                                              |
| `cambiarEstado(...)`    | Cambiar estado a tarea inexistente        | Mock repo devuelve null                        | Llamar `cambiarEstado(null)`               | Lanza excepción o muestra mensaje de error.                                                                                                                         |
| `agregarSubtarea()`     | Agregar subtarea válida                  | Mock repo devuelve tarea madre válida          | Llamar `agregarSubtarea()`                 | Subtarea agregada a la lista de subtareas de la tarea madre; interacción con mock para guardar cambios.                                                             |
| `agregarSubtarea()`     | Agregar subtarea con subtareas existentes | Mock repo devuelve tarea madre válida          | Llamar `agregarSubtarea()`                 | Lanza `IllegalArgumentException` porque una subtarea no puede tener subtareas.                                                                                      |
| `usuariosConActividades()` | Asociar actividades a usuarios          | Mock repo tiene actividades y usuarios válidos | Llamar `usuariosConActividades()`          | Actividades asociadas correctamente a los usuarios en el mock repo.                                                                                                 |
| `filtrarPorEstado()`    | Filtrar tareas abiertas                   | Mock repo devuelve lista de tareas             | Llamar `filtrarPorEstado()` con "ABIERTA" | Devuelve lista de tareas abiertas.                                                                                                                                  |
| `filtrarPorEstado()`    | Filtrar tareas con estado inexistente     | Mock repo devuelve lista vacía                 | Llamar `filtrarPorEstado()` con "INVALIDO" | Devuelve lista vacía o lanza excepción por estado inválido.                                                                                                         |
| `filtradoPorEtiquetas()`| Filtrar por etiqueta URGENTE             | Mock repo devuelve lista de tareas             | Llamar `filtradoPorEtiquetas()` con "URGENTE" | Devuelve lista de tareas etiquetadas como "URGENTE".                                                                                                                |
| `filtradoPorUsuarios()` | Filtrar actividades por usuario existente | Mock repo devuelve usuario con actividades     | Llamar `filtradoPorUsuarios()`             | Devuelve lista de actividades asociadas al usuario.                                                                                                                 |
| `filtradoPorUsuarios()` | Filtrar actividades por usuario no existente | Mock repo devuelve null                        | Llamar `filtradoPorUsuarios()`             | Muestra mensaje de error indicando que el usuario no existe.                                                                                                       |
