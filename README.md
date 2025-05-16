# DESARROLLO DE LA ACTIVIDAD:

Primero, la actividad pedía seleccionar entre uno de dos analizadores de códigos, o detekt o ktlint,
yo he usado detekt que además, te hace escribir mucho menos en el gradle ya que le puedes poner un parámetro que te pone una configuración por defecto. Para integrarlo en el proyecto, como estoy usando gradle, al final solo hay que poner el plugin necesario (que en este caso sería detekt) que además, está en la página oficial de detekt que también está enlazada en la moodle con el ejercicio. Luego de cargar el gradle con este plugin, se debe de poner también la configuración del detekt dentro del archivo de configuración del gradle. La configuración es bastante fácil de poner y lo único que hay que hacer es poner detekt y abrir dos llaves y poner dentro de ahí toda la configuración necesaria.

![IMAGEN DEL GRADLE](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/config_detekt.png)

Después de esto hay que abrir el terminal desde la carpeta raiz de nuestro proyecto gradle y ahí tenemos que ejecutar ./gradlew detekt para que nos ejecute el analizador de código, después de esto, nos enseñará por terminal un listado con todos los errores que tiene nuestro código, como la actividad pide hablar de al menos 5, voy a detenerme a detallar 5 de estos errores que me ha dado a mi el comando.

![IMAGEN TESTEO](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/prueba_detekt.png)

En la imágen se ven muchísimos errores más, tantos que ni si quiera puedo enseñarlos en una sola captura, pero esto es normal ya que detecta absolutamente cualquier cosa que no tiene porque ser un fallo en sí, si no más una recomendación. Voy a listar 5 de esos errores que me han llegado a salir y voy a solucionarlos (que de eso trata el ejercicio vaya):

1. **[NestedBlockDepth]**  
_Ejemplo:_ `Function cargarActividades is nested too deeply.`  
**Descripción detallada:**  
Este error indica que una función contiene demasiados niveles de anidamiento (por ejemplo, varios if, for, while, try-catch uno dentro de otro). Un código muy anidado es difícil de leer, entender y mantener, ya que obliga a seguir la lógica saltando entre muchos bloques internos. Además, dificulta la reutilización y las pruebas.

**SOLUCIÓN:**
Como tenemos un tochaco de código donde se está procesando una línea y además de procesarla estoy también cargando la información de la actividad, puedo separar en funciones más pequeñas para además seguir los principios de independencia solid y hacer una programación mucho más limpia y pequeñita además de que se podría usar esa función para otros casos. Esta programación es como más nos recomienda nuestro profesor que hagamos pero aveces no le hago caso y pasan estas cosas si es que...

FUNCIÓN ANTES:

![FUNCION 1 ANTES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_1_1.png)

FUNCIÓN DESPUÉS:

![FUNCION 1 DESPUES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_1_2.png)

ENLACE AL COMMIT (PD: ESTE COMMIT COMPARTE LOS 2 PRIMEROS ERRORES PORQUE SE ME OLVIDÓ HACER UN COMMIT ESPECÍFICO POR CAMBIO):

[ENLACE AL COMMIT](https://github.com/Luismi0202/TaskManager-LGOMDOM/commit/00247084d45349f73bc5fb6bc89ae1e11bf67131)
Como se ve en las imágenes, he creado una función de procesar la línea y cargaractividad la llamará para procesar cada linea deserializada en el repo de actividades.

2. **[LongParameterList]**  
_Ejemplo:_ `The function creaInstancia(...) has too many parameters.`  
**Descripción detallada:**  
Cuando una función recibe muchos parámetros, suele ser señal de que está asumiendo demasiadas responsabilidades o que los datos no están bien agrupados. Esto complica el uso de la función, aumenta la probabilidad de errores al llamar y dificulta su mantenimiento.

**SOLUCIÓN:**
Crear una data class para los parámetros de evento y así estaríamos poniendo esos 6 parámetros en una clase y solo tendríamos que decirle a esta función de crearInstancia que lo que le pasamos será esa dataclass y ya luego pues cogerá cada propiedad e instanciará una clase con ello. Esta función es usada en más partes del código así que habrá que cambiar de manera que en vez de pasarle 6 cadenas de string, se almacenen en la data class evento instanciandola.

DATA CLASS:

![DATA CLASS](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_2_1.png)

FUNCIÓN ANTES:

![FUNCION ANTES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_2_2.png)

FUNCIÓN DESPUÉS:

![FUNCION DESPUES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_2_3.png)

CAMBIO EN PARTES DEL CÓDIGO:

![CAMBIO EN PARTES DEL CÓDIGO](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_2_4.png)

ENLACE AL COMMIT (PD: ESTE COMMIT COMPARTE LOS 2 PRIMEROS ERRORES PORQUE SE ME OLVIDÓ HACER UN COMMIT ESPECÍFICO POR CAMBIO):

[ENLACE AL COMMIT](https://github.com/Luismi0202/TaskManager-LGOMDOM/commit/00247084d45349f73bc5fb6bc89ae1e11bf67131)

3. **[TooGenericExceptionCaught]**  
_Ejemplo:_ `The caught exception is too generic. Prefer catching specific exceptions...`  
**Descripción detallada:**  
Capturar excepciones genéricas como `Exception` o `Throwable` puede ocultar errores inesperados y hacer que el código ignore problemas graves o difíciles de detectar. Además, dificulta el diagnóstico de fallos, ya que se pierde información sobre el tipo de error real. Es mejor capturar solo las excepciones que realmente se esperan y pueden manejarse de forma segura. También he de decir que yo lo que suelo hacer es tirar un Exception pero siempre hago yo los throws de manera que siempre acabo detallando porque es el error solo que meto todo en el mismo catch, realmente no considero que sea algo tan grave porque son las formas de programar de cada uno, pero igualmente, vamos a solucionar un fallo en uno de estos casos.

**SOLUCIÓN:**
Especificar la excepción en vez de usar una excepción genérica Exception para todos los errores.
Voy a coger de ejemplo la función de leer el archivo que se encuentra en el objeto Utils, esa función es bastante intuitiva saber que hace ya que como bien dice el nombre, lo que hará será leer la ruta que le pasemos y devolvernosla en forma de código, es decir, lee un fichero y si deserializar esa información de alguna forma, podemos hacerlo.
Como bien dije antes, suelo tirar excepciones globales por dos cosas, una porque las suelo controlar yo pero es que también hay muchos errores que pueden darte las cosas, no puedo saber todos los errores que hay ya que muchos paquetes como el de leer archivos genera los suyos propios, en este caso, generaba un error de entrada y salida, que en verdad se puede mirar fácil investigando pero al final te ahorras más tiempo poniendo el exception y luego refactorizandolo dándonos cuenta gracias a detekt.

FUNCION ANTES (ERROR EXCEPTION GLOBAL):

![FUNCION ANTES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_3_1.png)

FUNCION DESPUES (ERROR EXCEPTION ESPECIFICO DE ENTRADA Y SALIDA IOEXCEPTION):

![FUNCION DESPUES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_3_2.png)

ENLACE AL COMMIT DE ERROR 3:

[ENLACE AL COMMIT](https://github.com/Luismi0202/TaskManager-LGOMDOM/commit/499dff4223fc63acd67f2e318ececb41e4aa9031)

4. **[PackageNaming]**  
_Ejemplo:_ `Package name should match the pattern: [a-z]+(.[a-z][A-Za-z0-9])`  
**Descripción detallada:**  
Los nombres de los paquetes en Kotlin deben estar en minúsculas y seguir un formato específico (por ejemplo, `com.ejemplo.proyecto`). No seguir esta convención puede causar problemas de compatibilidad, dificultar la búsqueda de clases y afectar la organización del proyecto. Además, algunos sistemas de archivos pueden ser sensibles a mayúsculas/minúsculas, lo que puede provocar errores al compilar o ejecutar el proyecto en diferentes entornos.

**SOLUCIÓN:**
Este error es increíblemente tonto pero no sé si es más tonto el error o yo. Como se aprecia en la primera imágen, tenía TODOS los paquetes con la primera en mayúscula, y esto en kotlin la regla es que los paquetes deben de ir siempre en minúsculas y las clases son las que tienen la primera inicial en mayúscula. Vamos que lo único que hay que hacer es refactorizar y renombrar esos package para poner todas las letras primeras en minúsculas.

PAQUETES ANTES

![PAQUETES ANTES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_4_1.png)

PAQUETES DESPUES

![PAQUETES DESPUES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_4_2.png)

ENLACE AL COMMIT DE ERROR 4:

[ENLACE AL COMMIT](https://github.com/Luismi0202/TaskManager-LGOMDOM/commit/a091b6003780ee141c66663318c0c5164522b7ca)

5. **[WildcardImport]**  
_Ejemplo:_ `accesodatos.* is a wildcard import. Replace it with fully qualified imports.`  
**Descripción detallada:**  
Usar imports con asteriscos (`*`) importa todas las clases de un paquete, lo que puede provocar conflictos de nombres si diferentes paquetes contienen clases con el mismo nombre. Además, dificulta saber qué clases se están utilizando realmente en el archivo, reduciendo la claridad y el control sobre las dependencias. Es mejor importar solo las clases necesarias de forma explícita.

**SOLUCIÓN:**
Este es otro error bastante tonto ya que lo único que hay que hacer es no importar todas las clases del paquete si no las vamos a utilizar. Voy a cambiar el import de todas las clases de acceso a datos y voy a importar únicamente las que necesite realmente el programa. Si el IDE nos pone automáticamente que importa todas las clases de un paquete eso quiere decir que si usa todas. De hecho, al cambiar estos importes, el ide me ha dicho que en ActividadService usaba todas las de dominio y es normal ya que es el que gestiona principalmente el programa

IMPORTS ANTES:

![IMPORTS ANTES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_5_1.png)

COMO HACERLE LOS IMPORTS UNO A UNA DE FORMA SENCILLA GRACIAS AL IDE:

![COMO HACER IMPORTS DE FORMA SENCILLA](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_5_2.png)

IMPORTS DESPUES:

![IMPORTS DESPUES](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/DETEKT_5_3.png)

ENLACE AL COMMIT DE ERROR 5:
[ENLACE AL COMMIT](https://github.com/Luismi0202/TaskManager-LGOMDOM/commit/731f6997cf594ba89a2a78f820f48d474837b67a)


# CAMBIO DE CONFIG POR DEFECTO EN DETEKT
He modificado una de las opciones de configuración de este de analizador de errores. Para eso pues me metí en el gradle como vimos antes y esta vez le añadí una línea más a esa configuración, esa linea es la de baseline, que hará que los errores que se hayan detectado ya en una de las pasadas no vuelvan a salir, sino que se ignoran, esto es útil para detectar los nuevos errores que se produzcan en el código.

![LINEA AÑADIDA](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/CAMBIO_CONFIG.png)

![COMPROBACION](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/PRUEBA_CAMBIO_CONFIG.png)

En la segunda imagen se ve la comprobación y como se puede apreciar, ya no es como antes que toda la terminal estaba llena de muchas líneas de errores tal es así que no me cabían ni en la captura, sino que me sale un solo error y ya se ve el final de la terminal.

# DETALLE IMPORTANTE

En cada uno de estos cambios he ido probando el programa para comprobar que seguía funcionando. La captura que voy a adjuntar es la del programa después de hacerle todos los cambios, como se ve, sigue funcionando correctamente.

![CAPTURA PRUEBA](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/linting/IMAGENES/PRUEBA_DESP_DETEKT.png)
