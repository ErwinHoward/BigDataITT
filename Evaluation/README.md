# Evaluative practice Unit 2

### Imports 
```scala
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
```

###  1. Upload to an Iris dataframe.csv located in https://github.com/jcromerohdz/iris, elaborate the data liempieza necessary to be processed by the following algorithm (Important, this cleaning must be by middle of a Scala script in Spark). A). Use Spark's Mllib library multilayer Machine Learning algorithm perceptron*/
```scala
val datairis = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")
```

### 2. What are the names of the columns?
```scala
println(datairis.columns.toSeq)
```

### 3. What is the scheme like?
```scala
datairis.printSchema()
```

### 4. Print the first 5 columns.
```scala
datairis.select(datairis.columns.slice(0,5).map(m=>col(m)):_*).show()
```

### 5. Use the describe() method to learn more about the data in the DataFrame.

*In this case, to obtain information from the DataFrame, the name of our dataFrame “datairis” is used, followed by the .describe() method followed by .show()*
```scala
datairis.describe().show()
```

### 6. Make the pertinent transformation for the categorical data which will be our labels to classify.

*We declare a VAssembler vector to classify our categorical variables, in this case it will be the “sepal_length”, “sepal_width”, “petal_length” and “petal_width” columns, from this vector a new dataframe will be created with the features column.*

*The transformation of our main dataframe "datairis" is performed with the created vector VAassembler.*

*Finally, the result of the transformation is displayed.*
```scala
val VAssembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val Output = VAssembler.transform(datairis)
Output.show()
```
*A new "label" column is created which will contain the labels indexed as StringIndexer, since the Species column of the "datairis" dataframe will be used and it contains data of type string.*

*The "indexed” variable will save the result of the transformation of the labelIndexer and Output, which stores the dataframe with the last columns added.*

*Finally, the .show() method is used to show that the “label” column has been added.*
```scala
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("label").fit(datairis)
val indexed = labelIndexer.transform(Output)
indexed.show()
```

### 7. Build the classification model and explain its architecture.

*To use the Multilayer Perceptron Classifier model, the seed is established and the proportion in which the data will be randomly separated is established, in this case 70% - 30% which will be used for training and tests respectively, then the layers of the neural network.*

*Then the new trainer is created and parameters are assigned to it.*

*Finally, the model is trained.*
```scala
val splits = indexed.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

val layers = Array[Int](4, 5, 4, 3)
// create the trainer and set its parameters
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// train the model
val model = trainer.fit(train)
```

### 8. Print the model results

*The precision of the results obtained in the tests is calculated, and finally this calculated precision is displayed.*
```scala
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator()
.setMetricName("accuracy")

println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```
