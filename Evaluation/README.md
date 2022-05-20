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