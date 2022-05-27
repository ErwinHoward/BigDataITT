 Unit-2
# Practice 4 - Gradient-boosted tree classifier

### Imports 

``` 
    import org.apache.spark.ml.Pipeline
    import org.apache.spark.ml.classification.DecisionTreeClassifier
    import org.apache.spark.ml.classification.DecisionTreeClassificationModel
    import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
    import org.apache.spark.mllib.util.MLUtils
``` 

### Load and parse the data file into a DataFrame.

``` 
    val data = MLUtils.loadLibSVMFile(sc, "data/mllib/sample_libsvm_data.txt").toDF()
``` 

### Add the metadata to the label column. Adjust the dataset to include all the column labels in the index.

``` 
    val labelIndexer = new StringIndexer()
    .setInputCol("label")
    .setOutputCol("indexedLabel")
    .fit(data)
``` 

### Split the data into training and test sets (70% - 30% ratio)

``` 
    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
``` 

### Create the DecisionTree model.    

``` 
    val dt = new DecisionTreeClassifier()
    .setLabelCol("indexedLabel")
    .setFeaturesCol("indexedFeatures")
```     

### Convert indexed tags back to original tags.

``` 
    val labelConverter = new IndexToString()
    .setInputCol("prediction")
    .setOutputCol("predictedLabel")
    .setLabels(labelIndexer.labels)
``` 

### Chain indexers and the tree created in a Pipeline.

``` 
    val pipeline = new Pipeline()
    .setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
``` 

### Train the model and run the indexers.

```     
    val model = pipeline.fit(trainingData)
``` 

### Create prediction.

``` 
    val predictions = model.transform(testData)
``` 

### Select the columns that you want to display.

``` 
    predictions.select("predictedLabel", "label", "features").show(5)
``` 

### Select prediction, label, and test error.

``` 
    val evaluator = new MulticlassClassificationEvaluator()
    .setLabelCol("indexedLabel")
    .setPredictionCol("prediction")
    .setMetricName("precision")
    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))
    
    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    println("Learned classification tree model:\n" + treeModel.toDebugString)

``` 

# Practice 3

# Given the pseudocode of the Fibonacci sequence in the provided link, implement Algorithm 1, Algorithm 2, Algorithm 3, Algorithm 4, Algorithm 5 with Scala

### Algorithm 1
Defines the value of the variable "num" and fibonacci1, therefore if the value of "num" is less than 2 it returns the value, if it does not define the value of the variable and subtracts 1 from the variable 

def fibonacci1(num: Int):Int ={
  if(num < 2){
    return num
  }else{
  return fibonacci1(num-1)+fibonacci1(num-2)
  }
}

### Algorithm 2
We put the value of fibonacci2 in val n , in the variable phi we assign 1 sqrt of 5 and divide everything by 2 and  n var j we assign the value of var pow squared - (1 - golden) squared and divide by root squared 5 Select the fibonacci2 function, if num is less than 2, it returns the value n, because fibonacci2 0 = 0 and fibonacci2 1 = 1 otherwise it returns the value j in the final result of the fibonacci2 function 

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


### Algorithm 3
Define the Fibonacci function 3 and get the value n, create three variables, initialize loop k from 1 to n number, c(0) equals b(1) plus a(0), result c = 1, a takes the value of b, b takes the value of c and returns the value of a 

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


### Algorithm 4
The fibonacci4 function is specified, the number of uses is 6, and it is specified by two variables. The loop is initialized to k, b is 1, so b equals b(1) plus a(0), the result is 1, a is 0, so a equals 1 - 0, the result is 1, and the loop starts again with the new values, in this case b-1 and a-1 and return value 

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


### Algorithm 5
The function fibonacci5 is defined, the value n is selected, we create an array of n 1 positions, and the positions 0 and 1 of the vector are set to 0 and 1, respectively, because fibonacci5 is from 0 to 0 and from 1 to 1, if n value less than 2 returns the same value as n, the loop goes through vector values, Fibonacci process 5 with vector values, since the value is 7, the Fibonacci value 5 adds to 8 and the Fibonacci value of 5 Fibonacci5 the value varies from 5 to 5 The result is 13 and returns the value of the vector at position n 

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
develop
