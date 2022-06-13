# Unit 3

## 1. Import a simple session
With this library it allows us to make a simple session in spark
```scala
import org.apache.spark.sql.SparkSession
```

## 2. Use lines of code to minimize errors
```scala
import org.apache.log4j.Logger
import org.apache.log4j.Level
Logger.getLogger("org").setLevel(Level.OFF)
Logger.getLogger("akka").setLevel(Level.OFF)
```

## 3. Create an instance of the Spark 
In the variable “spark” an instance of our Spark session will be created
```scala
val spark = SparkSession.builder().getOrCreate()
```

## 4. Import the Kmeans library for the clustering algorithm.
With the KMeans library it will help us with the clustering algorithm
```scala
import org.apache.spark.ml.clustering.KMeans
```

## 5. Load the Wholesale Customers Data dataset
Our .csv file is called "Wholesale customers data.csv" and the data from our file is going to be saved in the data variable
```scala
val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("Wholesale customers data.csv")
```

## 6. Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data
We will select certain columns and save them in the variable “feature_data”
```scala
val feature_data = (data.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen"))
```

## 7. Import Vector Assembler and Vector
```scala
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

## 8. Create a new Vector Assembler object for the feature columns as a input set, recording that there are no labels
The VAssembler object is created, which contains a features column with the characteristics of the dataset "data"
```scala
val VAssembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")
```

## 9. Use the assembler object to transform feature_data
We use the previously created assembler object to make the transformation of “feature_data”, this transformation has the name of “features”
```scala
val features = VAssembler.transform(feature_data)
```

## 10.Create a Kmeans model with K=3
To create the Kmeans model we assign the number of groups in which the data (3) and the seed (1) will be divided.
```scala
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)
```

## 11.Evaluate the clusters using the sum of squared errors within the WSSSE set and print the centroids.
Next, the variable where the 3 data groups of the model (WSSSE) will be evaluated is declared, in the next two lines only the result is printed, which is the squared error, and finally the centroids are printed
```scala
val WSSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSSE")
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```
