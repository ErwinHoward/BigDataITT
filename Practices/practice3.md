# Practice 3

###  1. Create a list called "lista" with the elements "rojo", "blanco", "negro"
    //Declare the list and the elements

        var lista =List("rojo","blanco","negro")

### 2. Add 5 more items to "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
    //Adding the new elements to the list

        lista = "verde" ::"amarillo" ::"azul" ::"naranja" ::"perla" :: lista 

### 3. Fetch items from "lista" "verde", "amarillo", "azul"
    //For loop: from 0 to 2, which is the position of the eleemnts, and print i

        for(i <- 0 to 2){ 
        println(lista(i))
        }

### 4. Create an array of numbers in the range 1-1000 in steps of 5 at a time
    //Use range to set the step of the array, and establish the range from 1 to 1000

        Array.range(1, 1000, 5)

### 5. What are the unique elements of the list List(1,3,3,4,6,7,3,7) use conversion to set
    // Create the list with its elements, and convert the list to a set

        val lista=List(1,3,3,4,6,7,3,7)
        lista.toSet

### 6. Create a mutable map named names containing the following: "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
    //Create the mutable map with its elements

        val nombres = Map( ("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"));

#### 6.a Print all the keys on the map
    //Use .keys next to the map's name to print the keys

        nombres.keys;

#### 6.b Add the following value to the map("Miguel", 23)
    //Create a new variable and assign the new element to the map using the operator "+"
        var suma1 = nombres + ("Miguel" -> 23);
