//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo

val diameter : Double = 10
val radio = diametro / 2
radio

//2. Desarrollar un algoritmo en scala que me diga si un número es primo

val numero : Int = 2
var primo : Boolean = true

for(i <- Range(2, numero)) {
  if((numero % i) == 0) {
    primo = false
  }
}
if(primo){
  println("Es Primo")
} else {
  println("No es Primo")
}

//3. Dada la variable  var bird = "tweet", utiliza interpolación de strings para imprimir "Estoy ecribiendo un tweet"

var bird = "tweet"
printf("Estoy escribiendo un %s", bird)

//4. Dada la variable var mensaje = "Hola Luke yo soy tu padre!" utiliza slice para extraer la secuencia "Luke"

var mensaje = "Hola Luke yo soy tu padre!"
mensaje.slice(5,9)


//5. Cúal es la diferencia entre value (val) y una variable (var) en scala?

//Un val es una referencia constante. Por lo tanto, no se puede asignar un nuevo objeto a un val que ya se ha asignado.
//Una var es una variable de referencia, similar a las variables en lenguajes como Java. Se pueden asignar diferentes objetos a una var libremente, siempre que el objeto dado tenga el mismo tipo con el que se declaró la var.

//6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el número 3.1416

val tupla = (2,4,5,1,2,3,3.1416,23)
tupla._7