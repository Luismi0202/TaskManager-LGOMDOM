[1]  
**1.a** ¿Qué herramienta has usado, y para qué sirve?  

Detekt, que sirve para analizar tu código de kotlin y detectar errores, que no tienen porque ser graves si no que también puede decirte de que no sigues algunas reglas de convención como me ha pasado a mí con algunos nombres que los tenia capitalizados cuando no eran clases o que importaba cosas que no usaba. Estos errores no son algo grave para el código pero si que puede afectar a la hora del rendimiento y está bien que se use para que el código esté más kimpio, entendible y estructurado.

**1.b** ¿Cuáles son sus características principales?  

Detekt analiza el código de kotlin y encuentra errores y malas prácticas, detecta incumplimientos de convenciones de estilo y nombres y genera informes claros y detallados sobre los problemas que se pueden encontrar en el código de tu proyecto. Además, se integra bastante fácil con un plugin en el gradle, como hice en el mio.

**1.c** ¿Qué beneficios obtengo al utilizar dicha herramienta?

Puedo ver los errores y malas prácticas que he cometido y al final, me queda un error mucho más claro, limpio y detectando mis errores de forma sencilla.

[2]  
**2.a** De los errores/problemas que la herramienta ha detectado y te ha ayudado a solucionar, ¿cuál es el que te ha parecido que ha mejorado más tu código? 

Como digo en el readme, hay un error que me ha dado por tener una función con muchos parámetros y he usado una dataclass para no tener tanta cosa en esa función, al final hace que el código sea más limpio sin que tenga 6 parámetros en una función quedando todo super ilegible.

**2.b** ¿La solución que se le ha dado al error/problema la has entendido y te ha parecido correcta? 

Sí, he entendido el error y la solución que me ha dado, ya que al final es una buena práctica no tener tantas cosas en una función y usar dataclass para que quede más limpio y entendible.

**2.c** ¿Por qué se ha producido ese error/problema?

Pues como he dicho ya, este problema se ha producido por tener 6 parámetros en una función, que al final es un error de diseño, ya que no es necesario tener tantos parámetros y al final es mejor usar una dataclass para que quede más limpio y entendible.

[3]  
**3.a** ¿Qué posibilidades de configuración tiene la herramienta?  
Detekt tiene muchas configuraciones, como por ejemplo, puedes configurar el estilo de código, los errores que quieres que te detecte, los nombres de las clases y funciones, etc. Puedes configurar todo lo que quieras para que se adapte a tu proyecto y a tus necesidades.

**3.b** De esas posibilidades de configuración, ¿cuál has configurado para que sea distinta a la que viene por defecto?  

He configurado el hecho de que se me repitan los errores que ya he visto con el parámetro baseline en la config del gradle, esto viene bien ya que si nos hemos dado cuenta de los errores graves y los hemos apuntado, no vamos a querer que nos lo diga de nuevo, si cambiamos el código lo que queremos es que nos diga los nuevos errores.

**3.c** Pon un ejemplo de cómo ha impactado en tu código, enlazando al código anterior al cambio, y al posterior al cambio.

En las capturas del readme se puede ver perfectamente el antes y el después en muchos ejemplos. Por ejemplo, en  las importaciones que cambié el uso de asterisco para que me importará todas las clases de los paquetes y simplemente hice que el ide me importará lo necesario gracias a que se me detectó ese error.

[4]  
**4** ¿Qué conclusiones sacas después del uso de estas herramientas?

He aprendido a usar detekt y a configurarlo para que me ayude a mejorar mi código y a detectar errores que no son graves pero que al final hacen que el código sea más limpio y entendible. Además, he aprendido a usar dataclass para no tener tantas cosas en una función y hacer que el código sea más limpio y entendible. También he aprendido a usar el plugin de detekt en el gradle y a configurarlo para que se adapte a mis necesidades.