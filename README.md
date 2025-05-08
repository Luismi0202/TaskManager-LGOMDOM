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

| Método                   | Caso de prueba                                | Estado inicial del mock                                                                 | Acción                                  | Resultado esperado                                                               |
|--------------------------|-----------------------------------------------|---------------------------------------------------------------------------------------|-----------------------------------------|----------------------------------------------------------------------------------|
| agregarSubtarea(...)     | Tarea madre existente                         | mockRepo devuelve una lista con una tarea                                             | Llamar agregarSubtarea(...)             | Subtarea agregada con éxito, historial actualizado                               |
| agregarSubtarea(...)     | Tarea madre no existe                        | mockRepo devuelve una lista vacía                                                    | Llamar agregarSubtarea(...)             | Mensaje de error mostrado, historial no actualizado                              |
| cambiarEstado(...)       | Estado válido                                | mockConsola devuelve estado válido, mockRepo devuelve tarea existente                | Llamar cambiarEstado(...)               | Estado de tarea actualizado, historial actualizado                               |
| cambiarEstado(...)       | Estado inválido                              | mockConsola devuelve estado inválido                                                 | Llamar cambiarEstado(...)               | Mensaje de error mostrado, estado no actualizado                                 |
| filtrarPorEstado(...)    | Tareas con estado ABIERTA                    | mockRepo devuelve tareas con diferentes estados                                       | Llamar filtrarPorEstado(...)            | Se muestran solo tareas con estado ABIERTA                                       |
| filtrarPorEstado(...)    | Sin tareas en el repositorio                 | mockRepo devuelve una lista vacía                                                    | Llamar filtrarPorEstado(...)            | Mensaje indicando que no hay tareas                                              |
| anadirActividad(...)     | Datos válidos                                | mockConsola devuelve datos válidos                                                   | Llamar anadirActividad(...)             | Actividad añadida con éxito, historial actualizado                               |
| anadirActividad(...)     | Datos inválidos                              | mockConsola devuelve datos nulos o vacíos                                            | Llamar anadirActividad(...)             | Mensaje de error mostrado, historial actualizado con mensaje de error            |
| listarActividades(...)   | Repositorio con actividades                  | mockRepo devuelve una lista de actividades                                           | Llamar listarActividades(...)           | Actividades listadas, historial actualizado                                      |
| listarActividades(...)   | Repositorio vacío                            | mockRepo devuelve una lista vacía                                                    | Llamar listarActividades(...)           | Mensaje indicando que no hay actividades                                         |
| filtrarPorEtiquetas(...) | Tareas con etiqueta URGENTE                  | mockRepo devuelve tareas con diferentes etiquetas                                     | Llamar filtrarPorEtiquetas(...)         | Se muestran solo tareas con etiqueta URGENTE                                     |
| filtrarPorEtiquetas(...) | Sin tareas con etiquetas                     | mockRepo devuelve una lista vacía                                                    | Llamar filtrarPorEtiquetas(...)         | Mensaje indicando que no hay tareas                                              |
