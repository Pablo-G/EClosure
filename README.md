# EClosure
[Autómatas y Lenguajes Formales] Proyecto 1. EPSILON-Cerradura

##¿Cómo se compila?
Este proyecto cuenta con un archivo `Build.xml` asi que puedes usar [Apache Ant](http://ant.apache.org/) para compilarlo :)

Desde tu linea de comandos ejecuta:
```
ant Compile
```
Para compilar el proyecto.
```
ant Clean
```
Para restaurar el proyecto a su configuracion inicial.

**(Nota!)** Durante la ejecucion de la aplicación se pueden crear archivos `.pdf` o `.dot`, este comando **no** eliminará estos archivos.
```
ant EClosure.jar
```
Para compilar y generar el _preciado_ `.jar`
```
ant Document
```
Para compilar y generar la documentación del proyecto.

##¿Cómo se usa?
EClosure cuenta con una simple, pero bastante completa interfaz gráfica.
Para ponerla en marcha, desde tu linea de comandos, ejecuta:
```
java -jar EClosure.jar
```
>Recuerda compilar el proyecto antes :P

Aparecerá una ventana como esta:
![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura1.PNG)
Podemos notar que en la tabla de transiciones ya nos aparece un estado (Inicial), lamentablemente nunca nos podremos deshacer de él,
pero si se desea después podremos cambiar su nombre y volverlo estado final.

* Agregar símbolos a Σ.

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura2.PNG)
* Agregar un estado a Q.

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura3.PNG)
* Modificar un estado de Q.

Selecciona un estado e inserta en el cuadro de texto el nuevo nombre que le quieras dar. (Si no deseas renombrarlo pon el mismo nombre)

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura4.PNG)
* Eliminar un estado de Q.

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura5.PNG)
* Crear/Modificar una transición.

EClosure detectará automáticamente si estas creando o modificando una transición, asi que no te preocupes por eso.

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura6.PNG)
* Eliminar una transición.

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura7.PNG)
* Generar código `.dot` del autómata.

Al apretar el boton Genera Código de Gráfica este aparecerá en la consola. Adicionalmente se creará un archivo `.dot` el cual podras
abrir con una aplicación como [Graphviz](http://www.graphviz.org/).

![alt tag](http://www.catalogosymas.com/pablo/EClosure/Captura8.PNG)
* Calcular la ε-cerradura.

Cuando aprites el boton Calcular ε-cerradura empezará a desplegarse en la consola la ε-cerradura de cada estado en Q del autómata.
También se creará un archivo `.pdf` con esta información. :)

##¿Por qué aparece un horrible cuadro gris del lado derecho de la ventana de la aplicación?
>Amm La idea era que en esa parte apareciera en tiempo real la gráfica del autómata. Planeé usar una libreria de Graphviz para hacer esto.
Para mi desgracia al momento de implementar este proyecto algo raro le sucedió al sitio de Graphviz y no pude accesar para descargar las
cosas que tuviera que descargar. Espero en un futuro tomarme un tiempo para completar EClosure. Promise!
