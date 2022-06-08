# Practice 6

### Import the "LinearSVC" library.
```scala
import org.apache.spark.ml.classification.LinearSVC
```

### Load training data
```scala
val training  = spark.read.format("libsvm").load("C:/Spark/spark-2.4.8-bin-hadoop2.7/data/mllib/sample_libsvm_data.txt")
```

### Set the maximum number of iterations and the regularization parameter 
```scala
val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
```

### Fit the model
```scala
val lsvcModel = lsvc.fit(training)
```

### Print the coefficients and intercept for linear svc
```scala
println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
```