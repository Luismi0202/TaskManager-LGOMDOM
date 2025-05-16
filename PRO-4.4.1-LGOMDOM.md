# Desarrollo de la actividad

Esta actividad es muy sencilla, consiste en algo que ya hemos estado haciendo en programación y que de hecho en digitalización también nos pedían una parte de esta actividad (la de pasar la documentación a una página html con algun plugin, yo por ejemplo en python usé pdoc). Lo primero que se hace en esta actividad es documentar en kdoc, que es un plugin de kotlin que permite documentar el código de una manera más sencilla y que luego se puede exportar a html. Para ello, lo primero que hay que hacer es añadir el plugin al build.gradle del proyecto, para ello hay que añadir la siguiente línea al final del archivo:

![LINEA AGREGADA](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/4.4.1-LGOMDOM/IMAGENES/LINEA_ANIADIDA.png)

Luego de hacer esto, cuando tengamos todo el código documentado, hay que poner ./gradlew dokkaHtml en el terminal y ya nos pasaría todas las funciones que hayamos documentado con kdoc en html. Voy a dejar aquí unos hiperenlaces a las 3 clases que NO tenía documentadas antes de realizar esta práctica, hay partes que ya tenía documentadas a lo mejor pero no eran clases enteras, asi que quizás se vean reflejadas a la hora de hacer el dokkahtml:

https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/782fe362102cc2aa7dd7c43d5ad7707da301339c/MEJORA-TASK/src/main/kotlin/Servicios/ActividadService.kt#L1-L430

https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/782fe362102cc2aa7dd7c43d5ad7707da301339c/MEJORA-TASK/src/main/kotlin/Dominio/Actividad.kt#L1-L58

https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/782fe362102cc2aa7dd7c43d5ad7707da301339c/MEJORA-TASK/src/main/kotlin/AccesoDatos/RepoActividades.kt#L1-L115

Ahora que he enseñado las clases que he documentado, es hora de pasar a la acción y ejecutar el comando para que nos haga la estructura de la carpeta dokka que luego moveremos a doc y nos ponga ahí dentro nuestras páginas html con la documentación de nuestro código.

![EJECUTANDO COMANDO](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/4.4.1-LGOMDOM/IMAGENES/BUILD_DOKKA.png)

Una vez hecho esto, se nos crea una carpeta en el build llamada "dokka" donde estarán todas nuestras páginas html, el que es importante de abrir es el index ya que es nuestra página principal (gracias lenguaje de marcas por enseñarme tanto). La carpeta de html que hay en dokka la moveremos a una carpeta que se llame /docs para poder así crear una github page

![ESTRUCTURA CREADA](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/4.4.1-LGOMDOM/IMAGENES/ESTRUCTURA_CREADA.png)

La siguiente captura que voy a poner muestra tanto un fragmento de nuestro código como ese mismo fragmento reflejado en la página, esto es para que quede claro que la documentación se ha hecho correctamente. De todas formas, gracias a lo que aprendí en digitalización, crearé una github page en este mismo repo con la carpeta correspondiente y lo dejare enlazado [AQUI]()

![IMAGEN DEMOSTRACIÓN](https://github.com/Luismi0202/TaskManager-LGOMDOM/blob/4.4.1-LGOMDOM/IMAGENES/MUESTRA_PAGINA.png)
