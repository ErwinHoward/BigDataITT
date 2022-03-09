//1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
var lista =List("rojo","blanco","negro")


//2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
lista = "verde" ::"amarillo" ::"azul" ::"naranja" ::"perla" :: lista 

//3. Traer los elementos de "lista" "verde", "amarillo", "azul"
 for(i <- 0 to 2){ 
        println(lista(i))
     }

//4. Crea un arreglo de número en rango del 1-1000 en pasos de 5 en 5

Array.range(1, 1000, 5) 

//5. Cuales son los elementos únicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversión a conjunto
val lista=List(1,3,3,4,6,7,3,7)
lista.toSet


//6. Crea una mapa mutable llamado nombres que contengan lo siguiente
     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
val nombres = Map( ("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"));

   //6 a . Imprime todas la llaves del mapa
nombres.keys;

   //6 b . Agrega el siguiente valor al mapa("Miguel", 23)
var suma1 = nombres + ("Miguel" -> 23);
