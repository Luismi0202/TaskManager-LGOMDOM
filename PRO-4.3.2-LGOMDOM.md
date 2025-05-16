# DESARROLLO EJERCICIO 

**REVISIÓN DEL CÓDIGO**
Para usar el inspeccionador de código del IDE, hay que darle click derecho a la carpeta src (es decir, la carpeta del contenido de nuestro módulo del proyecto) y ahí se le da a "Analyze" (analizar si lo tienes en español yo es que lo tengo en inglés) y luego se le da a la parte de inspeccionar código. Se pone como alcance todo el proyecto y se le da a aceptar. Una vez termine, saldrán todos los avisos y sugerencias de refactorización como se ve en las imágenes.

![PASOS A SEGUIR1]()

![PASOS A SEGUIR2]()

Los resultados son los siguientes:

![RESULTADOS]()

Vemos que hay 5 tipos de errores, en la imagen se pueden apreciar los "probable bugs", que es código que probablemente pueda dar errores (suele pasar sobre todo con llamadas a nulos), "redundant constructs", que como bien dice el nombre, son constructores que ya se han escrito y que lo estamos volviendo a usar, "style issues", que son errores que hace que nuestro código sea más dificil de leer o también puede ser que hay convenciones de kotlin que no estamos siguiendo, "grammar", que son errores de ortografía y los errores "typo", que son errores mal escritos en los identificadores, comentarios o cadenas de texto (vamos como los grammar pero en esas 3 cosas en concreto). Dejo aquí capturas de un error concreto para cada tipo:

-Probable bugs

![ERROR1]()

-Redundant constructs

![ERROR2]()

-Style issues

![ERROR3]()

-Grammar

![ERROR4]()

-Typo 

![ERROR5]()

**APLICACIÓN DE REFACTORIZACIONES** 

1: Extracción de método

Haré esta refactorización en el RepoUsuarios dentro del método cargar usuarios. Esta refactorización extrae la lógica de deseralización de usuario a un método privado, por ejemplo, deserializarYAgregarUsuario, aunque el IDE te lo crea con el nombre extracted.

ANTES DE REFACT/ PROCESO REFACT
![ANTES DE REFACT]()

DESPUES DE REFACT (AQUÍ LO DEL UTILS YA NO HARÍA FALTA PORQUE SE ME ESTÁ AÑADIENDO SOLO)
![DESPUES DE REFACT]()

2: Introducir parámetro objeto

Esto lo haré en evento.kt en el constructor secundarios que recibe muchos parámetros, lo que haré será crear un bojeto de parámetros (que podemos llamar como en el ejercicio de linting EventoParams) y hacer que el constructor lo reciba en vez de parámetros sueltos (vamos lo que hice en el ejercicio de linting esta vez me lo hará el solo). Se le da a refactor> introduce parameter object en la línea que seleccionemos y el mismo nos creará la data class y nos pondrá todo.

ANTES DE REFACT

![ANTES DE REFACT]()

DESPUES DE REFACT

![DESPUES DE REFACT]()

**NOTA: Este lo he refactorizado usando el atajo control + alt + shift + t y luego le das a introduce parameter object y te pedirá el nombre de la clase para agrupar los parámetros y tú mismo le puedes dar el nombre.**

3: Quitar código redundante (es decir, que se repite o no hace falta)

En la clase EstadoTarea, el propio IDE me estaba diciendo de refactorizar el hecho de repetir el nombre de la clase ya que como se estaba dentro de ella, no hacía falta instanciarla ni nada para preguntarle si eso era una de las entradas de la enum class, si no que se puede citar directamente la enum class.
Para la refactorización automática en este caso simplemente me he puesto encima y le he dado a lo de quitar la redundancia.

ANTES DE REFACT:

![ANTES DE REFACT]()

DESPUES DE REFACT

![DESPUES DE REFACT]()

**Desarrollo de pruebas**

Como hice esta rama apartir de la del main y no de la de pruebas unitarias, pues no tenía las pruebas unitarias anteriores pero de todas formas, esa era de solo una clase y no me venía también,a demas de que ya no se pide con DescribeSpec si no con JUnit. Lo que he hecho ha sido hacer 3 pruebas de 3 clases distintas antes de empezar a refactorizar y cuando he refactorizado realmente no he tenido que cambiar mucho esas pruebas ya que al final, son muy generales y estos cambios de refactorización realmente lo único que han hecho ha sido cambiar la forma en la que está el código para que este limpio pero funcionar funciona igual salvo la prueba de Evento, que tuve que crear un test con el EventoParams y otra sin (la primera vez fue sin porque lo tenia sin esa data class y luego se hizo así para quitar más parámetros). Dejo aquí el hyperlink de las 3 pruebas unitarias con JUnit que he realizado:

# RESPUESTA A LAS PREGUNTAS

[1]

**1.a ¿Qué code smell y patrones de refactorización has aplicado?**

Los code smells me han detectado código redundante, métodos que eran muy largos o que tenían más de una responsabilidad entonces ya no seguía el SOLID por tanto la convención de código limpio pues no la estaba siguiendo muy a raja tabla, había algunos métodos que tenían lógica similar y eso al final lo único que hace es que tenga código repetido y encima le empeore el rendimiento al ordenador por pedirle mal y tenía (porque gracias a estas cosas pues los quité como bien se ve en los ejercicios) un uso excesivo de parámetros en los constructores

Refactorizaciones he aplicado la de renombrar, introducir parámetros objetos y la de extraer métodos.

**1.b Teniendo en cuenta aquella funcionalidad que tiene pruebas unitarias, selecciona un patrón de refactorización de los que has aplicado y que están cubierto por los test unitarios. ¿Porque mejora o no mejora tu código? Asegurate de poner enlaces a tu código**

Apliqué el patrón de introducción parámetro objeto en la clase de Evento para así agrupar varios parámetros en un solo objeto y que el código quede más limpio y además, se pueden meter más parámetros de forma más sencilla. Dejo aquí el hiperenlace al código (otra vez pero bueno dejo constancia en donde es necesario)

[2]  

**2.a Describe el proceso que sigues para asegurarte que la refactorización no afecta a código que ya tenias desarrollado.**

Gracias a tener pruebas unitarias, es más sencillo comprobar esto porque al final si las ejecuto y me funcionan eso significa que me va bien, si no, también se puede ejecutar el programa y el propio terminal te dirá donde ha dado el fallo a la hora de compilarlo. Aunque, al hacer la refactorización con las herramientas del IDE, al final es una "refactorización segura", ya que el mismo IDE hace que se cambien las cosas como se deberían de cambiar en todos lados correctamente, así si le cambio el nombre a una función, pues se la va a cambiar también en todos los sitios donde a esta se le llame.

[3]  

**3.a ¿Que funcionalidad del IDE has usado para aplicar la refactorización seleccionada? Si es necesario, añade capturas de pantalla para identificar la funcionalidad.**

He usado la funcionalidad de refactorizar que tiene el propio IDE, que es la de renombrar, la de extraer método y la de introducir parámetro objeto. Para renombrar y extraer método simplemente le das click derecho y le das a refactorizar y luego a lo que quieras hacer, en este caso renombrar o extraer método. Para introducir parámetro objeto, simplemente seleccionas los parámetros que quieres agrupar y le das al atajo de teclado control + alt + shift + t y ahí le das a introducir parámetro objeto y ya te lo hace el solo.