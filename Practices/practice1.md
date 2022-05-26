# Practice 1 - Basic Statistics

## Correlation
### Imports 

    import org.apache.spark.ml.linalg.{Matrix, Vectors}
    import org.apache.spark.ml.stat.Correlation
    import org.apache.spark.sql.Row 

### Load the data.
    
    val data = Seq(
    Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
    Vectors.dense(4.0, 5.0, 0.0, 3.0),
    Vectors.dense(6.0, 7.0, 0.0, 8.0),
    Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
    )


### The mapping is applied to the previously loaded data and printed.
    
    val df = data.map(Tuple1.apply).toDF("features")
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1")

### The results obtained from the second correlation coefficient are shown.
    
    val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2")

## Chi - Square

### Import 

    import org.apache.spark.ml.linalg.{Vector, Vectors}
    import org.apache.spark.ml.stat.ChiSquareTest

### Load the vector data.

    val data = Seq(
    (0.0, Vectors.dense(0.5, 10.0)),
    (0.0, Vectors.dense(1.5, 20.0)),
    (1.0, Vectors.dense(1.5, 30.0)),
    (0.0, Vectors.dense(3.5, 30.0)),
    (0.0, Vectors.dense(3.5, 40.0)),
    (1.0, Vectors.dense(3.5, 40.0))
    )

### bDeclare a data frame with the label and features columns.

    val df = data.toDF("label", "features")

### Apply chi square to the dataframe and print the results.

    val chi = ChiSquareTest.test(df, "features", "label").head
    println(s"pValues = ${chi.getAs[Vector](0)}")
    println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
    println(s"statistics ${chi.getAs[Vector](2)}")


## Summarizer

### Import 

    import org.apache.spark.ml.linalg.{Vector, Vectors}
    import org.apache.spark.ml.stat.Summarizer

### Assign the input values to the vectors.

    val data = Seq(
    (Vectors.dense(2.0, 3.0, 5.0), 1.0),
    (Vectors.dense(4.0, 6.0, 7.0), 2.0)
    )

### Create a data frame with the features and weight columns.

    val df = data.toDF("features", "weight")

### Apply summary to the previous data frame with the mean and variance values. Then print the values.

    val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
    .summary($"features", $"weight").as("summary"))
    .select("summary.mean", "summary.variance")
    .as[(Vector, Vector)].first()
    
    println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")

### The second time the variance is calculated, the weight column is not used.

    val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
    .as[(Vector, Vector)].first()
    
    println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")