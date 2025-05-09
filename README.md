Para esta actividad, elegiré el servicio `ActividadService` de tu proyecto. Este servicio gestiona diversas operaciones relacionadas con actividades, como agregar subtareas, cambiar el estado de tareas y filtrar actividades. A continuación, estructuraré los pasos para cumplir con los objetivos de la actividad.

### Plan para Diseñar y Ejecutar Pruebas Unitarias para `ActividadService`

1. **Selección del Servicio**
   - Servicio elegido: `ActividadService`. (Hemos elegido este porque es el más completo además de que al final el programa va de gestionar esas actividades así que que mejor que usar el servicio de esas actividades). Además, el ejercicio pide que se use el servicio que tenga el repo inyectado y este es el único que tiene un repo inyectado en él.

2. **Identificación de Métodos Públicos**

Como realmente no hacía falta ningún método público porque al final lo que hago es hacer un companion object que instancie la función de arrancar el programa en el servicio y ese método del companion es lo que uso en el main así que todo trabaja en el servicio, pues realmente he tenido que cambiar un par de métodos a públicos (otros ya estaban porque no los puse privados como tal pero como no se usan deberían de serlos todos), al final, he elegido una variedad de métodos que algunos funcionas iguales que otros (como los de filtrado), así que realmente haciendo estas pruebas unitarias con estos métodos, es como haberlos hechos con todos. Se tienen que hacer con los métodos públicos porque si no entonces la clase del test no puede probar esos métodos fuera. Si tuvieramos que probar un método que es privado por narices, lo suyo sería cambiarlo un momentito a público. De hecho, se podría volver a dejar en privado todos los métodos que he seleccionado pero no lo voy a hacer por cuestiones obvias (que quiero que el testeo funcione correctamente cuando se ejecute)

Los métodos públicos que están en el servicio ActividadService (que es el que gestiona todo los métodos relacionados con las clases Tarea y Eventos) son...

-AgregarSubtarea()

-CambiarEstado()

-filtrarPorEstado()

-anadirActividad()

3. **Diseño de Casos de Prueba**

Ahora que tengo los métodos públicos identificados, lo que nos pide el ejercicio es hacer una tabla con los métodos y hacer 2 casos de pruebas y más o menos explicarlos poco a poco en esa tabla. Esto se hace con la idea de luego hacer los kotest. En la tabla, como bien se puede apreciar, hay 2 pruebas por métodos, que es como lo pide la tarea, uno tiene que ser de un caso de prueba donde el método haga lo suyo de forma correcta y otro tiene que ser con un fallo. Cuando realice la tabla y me puse manos a la obra con hacer los testing, me encontré con un par de fallos a la hora de hacer estos segundos testeos (o incluso los primeros) y es que muchas partes del código no controlaban errores o les faltaba por llamar a algunas clases como el historial. La tabla que se ha tenido en cuenta para hacer los testing es la siguiente (esta tabla la hice primero en un excel y luego le pedí a un modelo de lenguaje que me la pasará a markdown, mas que nada porque hacer tablas en markdown me cuesta un poco, imagino que habrá páginas pero lo suelo hacer asi):

| Método                   | Caso de prueba                                | Estado inicial del mock                                                                 | Acción                                  | Resultado esperado                                                               |
|--------------------------|-----------------------------------------------|---------------------------------------------------------------------------------------|-----------------------------------------|----------------------------------------------------------------------------------|
| agregarSubtarea(...)     | Tarea madre existente                         | mockRepo devuelve una lista con una tarea                                             | Llamar agregarSubtarea(...)             | Subtarea agregada con éxito, historial actualizado                               |
| agregarSubtarea(...)     | Tarea madre no existe                        | mockRepo devuelve una lista vacía                                                    | Llamar agregarSubtarea(...)             | Mensaje de error mostrado, historial no actualizado                              |
| cambiarEstado(...)       | Estado válido                                | mockConsola devuelve estado válido, mockRepo devuelve tarea existente                | Llamar cambiarEstado(...)               | Estado de tarea actualizado, historial actualizado                               |
| cambiarEstado(...)       | Estado inválido                              | mockConsola devuelve estado inválido                                                 | Llamar cambiarEstado(...)               | Mensaje de error mostrado, estado no actualizado                                 |
| filtrarPorEstado(...)    | Tareas con estado ABIERTA                    | mockRepo devuelve tareas con diferentes estados                                       | Llamar filtrarPorEstado(...)            | Se muestran solo tareas con estado ABIERTA                                       |
| filtrarPorEstado(...)    | Sin tareas en el repositorio                 | mockRepo devuelve una lista vacía                                                    | Llamar filtrarPorEstado(...)            | Mensaje indicando que no hay tareas                                              |
| anadirActividad(...)     | Datos válidos                                | mockConsola devuelve datos válidos                                                   | Llamar anadirActividad(...)             | Actividad añadida con éxito, historial actualizado                               |
| anadirActividad(...)     | Datos inválidos                              | mockConsola devuelve datos nulos o vacíos                                            | Llamar anadirActividad(...)             | Mensaje de error mostrado, historial actualizado con mensaje de error            |                                          |

**4.Implementación de los tests**

Primero que nada, hay que tener en cuenta que hay que crearse un módulo dentro de nuestro directorio de trabajo gradle (en el mismo src donde esta el main) que se llame "tests". Este realmente se crea casi automático ya que cuando le damos a crear un directorio dentro de src el propio IDE nos dice de crear "tests/kotlin", creandote así la estructura del modulo test con la carpeta de recursos tests kotlin. Ya eb esa carpeta se puede organizar todas las clases o archivos tests que queramos (yo al final uso una clase porque además se te pide que hagas un DescribeSpec).

Los describe spec sirven para agrupar pruebas que tienen un método en comun, así por ejemplo podemos hacer un Describe("pruebas de agregar subtarea") y luego con el it especificamos cada prueba ej: it("debería de crear una nueva tarea)"{y aqui iria el codigo de la prueba}

TODAS LAS PRUEBAS HECHAS CON DESCRIBESPEC (fui anotando con comentarios para ir sabiendo cuales tests iban fallando e ir cambiando esos hasta que funcionarán porque quería que fueran todas las pruebas):

https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/ed4ba47d63036d0c450d08029344bfb6bb0986d8/MEJORA-TASK/src/test/kotlin/ActividadServiceTest.kt#L1-L136

**5. EJECUCIÓN Y REPORTE DE RESULTADOS**

Me instalé un plugin llamado kotest que hace que las pruebas las puedas realizar desde la propia clase sin necesidad de ir a terminal y poner ./gradlew test, si no que tan solo le damos al botón de ejecutar y podemos incluso ir prueba a prueba comprobando (lo cual me ha venido perfecto para ir controlando aquellas pruebas que daban fallo). Vamos, al final es como ese triangulito verde que nos sale en el main para que podamos ejecutar el programa sin tener que hacerlo en terminal

![Foto de lo que me refiero](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/pruebas-unitarias/img/KOTEST_EJECUTADOS.png)

Cuando probamos las pruebas todas a la vez, vemos que todas se ejecutan correctamente (fui probando poco a poco hasta poder dar con todas correctas pero tuve muchos fallos por el camino como los que mencioné anteriormente por no controlar errores, hacer mal castings de las listas o por no llamar a todas las clases que hacían falta en el método a la hora de hacer la prueba).

![Foto de las pruebas ejecutadas](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/pruebas-unitarias/img/KOTEST_clase.png)

Como vemos, pasa 8/8 de los tests (es decir, todos) y tarda 623 milisegundos en hacer todas las pruebas.
