# Practice 2

###  1. Develop an algorithm in scale which calculates the radius of a circle

    //Declare a constant value (val) as a double and assign it the value of 10
    //Declare constant radio and assign the (diameter/2) result
    //Shows radio value

        val diametro : Double = 10  
        val radio = diametro / 2  
        radio   

### 2. Develop an algorithm in scala that shows if a number is prime
    //Declare an int variable
    //Declare a boolean variable

        val numero : Int = 2        
        var primo : Boolean = true  

    //Foor loop: If it's the module 0, then primo is false

        for(i <- Range(2, numero)) { 
        if((numero % i) == 0) {   
            primo = false           
            }
        }

    //If it's primo true, then Print "Es priemo", If it's primo false, then

        if(primo){                
        println("Es Primo")     
        } else {                  
        println("No es Primo")  
        }

### 3. Given the variable var bird = "tweet", use string interpolation to print "Estoy escribiendo un tweet"

    //Assign "Tweet" to bird
        var bird = "tweet"        

    //Print "Estoy scribiendo" adding the variable 'dird'
        printf("Estoy escribiendo un %s", bird)


### 4. Given the variable var mensaje = "Hola Luke yo soy tu padre!" use slice to extract the sequence "Luke"

    //Assign value to the the variable
        var mensaje = "Hola Luke yo soy tu padre!" 

    //Take elements from mensaje from 5 to 9"
        mensaje.slice(5,9)


### 5. What's the difference between value (val) and a variable (var) in scala?

    A val is a constant reference. So, once the value is assigned, it can't longer change.
    A var is a reference variable, similar to variables in languages like Java. 
    Different objects can be assigned to a var freely, as long as the given object has the same type as the var was declared with.


### 6. Given the tuple (2,4,5,1,2,3,3.1416,23) return the number 3.1416

    //Assign values to the tuple
        val tupla = (2,4,5,1,2,3,3.1416,23)
        
    //Return the element number 7 from the tuple
        tupla._7      