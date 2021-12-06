# Ejercicio 1: Problema del termostato

## Patrones de diseño

### Patrón Estado

Hemos implementado el Patrón *State* o *Estado* para desarrollar la solución al ejercicio del termostato, puesto que
cada termostato puede tener diferentes modos de funcionamiento independientes y que actúan de diferente manera ante
acontecimientos como el cambio de la temperatura. 

<img src="https://github.com/jorgeteixe/DS-43-08/blob/main/DS-43-08-PD/src/doc/e1/img/State%20Diagram.svg" width="550" height="550" alt="Diagrama de Estados">
<p></p>
Cada estado representa un modo de funcionamiento, que implementa una interfaz común al resto cumpliendo un 
*diseño por contrato* con el termostato. Así, este no se dará cuenta de qué estado está actualmente activo y no 
tendrá que preocuparse qué acciones tomar ante un cambio de la temperatura, ni preocuparse de las restricciones
en los cambios puesto que ya son los propios estados los que se encargan.
<p></p>
<img src="https://github.com/jorgeteixe/DS-43-08/blob/main/DS-43-08-PD/src/doc/e1/img/Class%20Diagram.svg" width="800" height="650" alt="Diagrama de Clases">

### Patrón Singleton

Este patrón básicamente lo usamos por un tema de eficiencia. Cada estado lo implementa, lo que quiere decir que cada uno
de estos es un punto de acceso global al termostato(s) y estas instancias sólo se crearán si son necesarias. Por ejemplo,
si tuviésemos un termostato que controla varias habitaciones o pisos, ¿sería lógico almacenar en memoria todos los estados
de cada termostato cuando estos son exactamente indénticos? Parece más lógico representarlos como una única instancia
y que sean compartidos.

## Principios de Diseño
### *SOLID*
* <b>Principio de Sustitución de Liskov</b>: En nuestra solución, este principio de diseño puede observarse cumpliendo el
principio de subcontratación de *Estado* y *EstadoConcreto*. La interfaz *Estado* define una serie de métodos que deben 
implementar los estados concretos con sus restricciones. Por ejemplo para el método *cambioEstado*, el estado concreto 
*Timer* lo redefine cumpliendo las restricciones iniciales del método y añadiendo una más que es que no se pueda 
cambiar directamente al modo *Program*. Por esto, cualquier objeto *Estado* podrá ser sustituído por un objeto *Timer*. 
Idem para el resto de estados concretos.

* <b>Principio de Inversión de la dependencia</b>: Este principio se ve reflejado mediante el atributo estado de la 
clase *Termostato*, este atributo es una interfaz *Estado* que define los métodos que deberán implementar todas las 
clases que extiendan de esta interfaz. Por tanto estamos evitando depender de un estado en concreto o tener que disponer
 de una instancia de todos ellos en el termostato. De esta manera cuando se hace un cambio de estado, este seguirá 
 llamando al mismo método sin enterarse de si el estado ha cambiado o en qué modo se encuentra (*Manual*, *Off*, etc).
 
* <b>Principio abierto-cerrado</b>: Se utiliza por ejemplo en el método *nuevaTemperatura* de la interfaz *Estado*
tenemos estados que actúan de diferente manera ante una actualización de la temperatura que se hace cada 5 minutos, el
estado *Program* activa o desactiva la calefacción según la temperatura de consigna, el *Timer* se desactivará pasado
un tiempo, el *Manual* solo actualiza el registro, etc. Por lo tanto desde la clase termostato, se utiliza la ligadura
dinámica para llamar al método *nuevaTemperatura* del estado dinámico que contiene en ese momento la clase Termostato.
Añadir un nuevo estado simplemente significaría añadir una nueva clase que extienda la interfaz *Estado* y que redefina
sus métodos.

También se usaron como referencias otros principios de diseño que son inherentes a los anteriormente citados, como el
principio de "*encapsula lo que varía*" (*Estado* y *Estado Concreto*), el principio de "*bajo acoplamiento*", "*Tell, 
don ́t ask*" (por ejemplo el termostato nunca le pregunta al objeto estado qué estado es, simplemente le ordena hacer 
cosas) y otros principios más genéricos como el "*DRY*" o "*KISS*".
