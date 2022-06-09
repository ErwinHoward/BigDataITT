 Unit-2
# Practice 3 - Random Forest Classifier

# Classification Example

### imports 
```scala
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.mllib.util.MLUtils
```

### Load and parse the data file.
```scala
val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")
```

### Split the data into training and test sets (30% held out for testing)
```scala
val splits = data.randomSplit(Array(0.7, 0.3))
val (trainingData, testData) = (splits(0), splits(1))
```

### Train a RandomForest model. Empty categoricalFeaturesInfo indicates all features are continuous.
```scala
val numClasses = 2
val categoricalFeaturesInfo = Map[Int, Int]()
val numTrees = 3 // Use more in practice.
val featureSubsetStrategy = "auto" // Let the algorithm choose.
val impurity = "gini"
val maxDepth = 4
val maxBins = 32

val model = RandomForest.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
  numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)
```

### Evaluate model on test instances and compute test error
```scala
val labelAndPreds = testData.map { point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
}
val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
println(s"Test Error = $testErr")
println(s"Learned classification forest model:\n ${model.toDebugString}")
```

### Save and load model
```scala
model.save(sc, "target/tmp/myRandomForestClassificationModel")
val sameModel = RandomForestModel.load(sc, "target/tmp/myRandomForestClassificationModel")
```

# Regression Example

### Imports
```scala
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel
import org.apache.spark.mllib.util.MLUtils
```

### Load and parse the data file
```scala
val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")
```

### Split the data into training and test sets (30% held out for testing)
```scala
val splits = data.randomSplit(Array(0.7, 0.3))
val (trainingData, testData) = (splits(0), splits(1))
```

### Train a RandomForest model. Empty categoricalFeaturesInfo indicates all features are continuous.
```scala
val numClasses = 2
val categoricalFeaturesInfo = Map[Int, Int]()
val numTrees = 3 // Use more in practice.
val featureSubsetStrategy = "auto" // Let the algorithm choose.
val impurity = "variance"
val maxDepth = 4
val maxBins = 32

val model = RandomForest.trainRegressor(trainingData, categoricalFeaturesInfo,
  numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)
```

### Evaluate model on test instances and compute test error
```scala
val labelsAndPredictions = testData.map { point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
}
val testMSE = labelsAndPredictions.map{ case(v, p) => math.pow((v - p), 2)}.mean()
println(s"Test Mean Squared Error = $testMSE")
println(s"Learned regression forest model:\n ${model.toDebugString}")
```

### Save and load model
```scala
model.save(sc, "target/tmp/myRandomForestRegressionModel")
val sameModel = RandomForestModel.load(sc, "target/tmp/myRandomForestRegressionModel")
```
=======
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
 develop
