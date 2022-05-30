//1. Develop an algorithm in scale which calculates the radius of a circle

val diametro : Double = 10  //Declare a constant value (val) as a double and assign it the value of 10
val radio = diametro / 2    //Declare constant radio and assign the (diameter/2) result 
radio                       //Shows radio value


//2. Develop an algorithm in scala that shows if a number is prime

val numero : Int = 2        //Declare an int variable and assign the value as 2
var primo : Boolean = true  //Declare a boolean variable

for(i <- Range(2, numero)) { //foor loop
  if((numero % i) == 0) {   //If it's the module 0, then
    primo = false           //primo is false
  }
}
if(primo){                //It's primo true, then
  println("Es Primo")     //Print "Es priemo"
} else {                  //It's primo false, then 
  println("No es Primo")  //Print "No es primo"
}


//3. Given the variable var bird = "tweet", use string interpolation to print "Estoy escribiendo un tweet"

var bird = "tweet"        //Assign "Tweet" to bird
printf("Estoy escribiendo un %s", bird)//Print "Estoy scribiendo" adding the variable 'dird'


//4. Given the variable var mensaje = "Hola Luke yo soy tu padre!" use slice to extract the sequence "Luke"

var mensaje = "Hola Luke yo soy tu padre!" //Assign value to the the variable
mensaje.slice(5,9)//Take elements from mensaje from 5 to 9"


//5. What's the difference between value (val) and a variable (var) in scala?

//A val is a constant reference. So, once the value is assigned, it can't longer change.
//A var is a reference variable, similar to variables in languages like Java. 
//Different objects can be assigned to a var freely, as long as the given object has the same type as the var was declared with.


//6. Given the tuple (2,4,5,1,2,3,3.1416,23) return the number 3.1416

val tupla = (2,4,5,1,2,3,3.1416,23)//Assign values to the tuple
tupla._7      //Return the element number 7 from the tuple
