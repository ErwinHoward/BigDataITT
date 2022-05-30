//Algorithm 4

//Algorithm 1
def fibonacci1(num: Int):Int ={
  if(num < 2){
    return num
  }else{
  return fibonacci1(num-1)+fibonacci1(num-2)
  }
}

fibonacci1(6)

//Algorithm 2

//all imports
import scala.math.sqrt 
import scala.math.pow
def fibonacci2(num: Double):Double ={
  var j = 0.0
  if(num < 2){
    return num
  }else{
    val aureo = (1+sqrt(5))/2
    j = pow(aureo,num)
    j = j - pow((1.0 - aureo),num)
    j = j / sqrt(5)

  }
  return j
}

fibonacci2(6)

//This algorithm needs three variables
                
var a: Int = 0     //Declaration of variables
var b: Int = 1
var c: Int = 0

def fibonacci3(n: Int): Int = {
    a = 0
    b = 1
    c = 0

    for (k <- Range(0, n)){
        c = b + a
        a = b
        b = c 
    }

    return a
}

fibonacci3(3)


var a: Int = 0
var b: Int = 1

def fibonacci4(n: Int): Int = {
    a = 0
    b = 1

    for (k <- Range(0, n)){
        b = b + a
        a = b - a
    }

    return b
}

fibonacci4(4)

def fibonacci5(n: Int): Int = {
    if (n < 2)
        return n
    else {
        var arr = new Array[Int](n + 1)
        arr(0) = 0
        arr(1) = 1
        for (k <- Range(2, n + 1)){
            arr(k) = arr(k -  1) + arr(k - 2)
        }
        return arr(n)
    }
}

fibonacci5(18)