# Unit 3

## 1. Import a simple session
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
```scala
val spark = SparkSession.builder().getOrCreate()
```
## 4. Import the Kmeans library for the clustering algorithm.
```scala
import org.apache.spark.ml.clustering.KMeans
```
## 5. Load the Wholesale Customers Data dataset
```scala
val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("Wholesale customers data.csv")
```
## 6. Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data
```scala
val feature_data = (data.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen"))
```