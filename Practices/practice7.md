# Practice 7 - Naive Bayes

### Imports 

    import org.apache.spark.ml.classification.NaiveBayes
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
    import org.apache.spark.sql.SparkSession 

### Specify the path from where the data is taken.
    
    val data = spark.read.format("libsvm").load("C:/spark/spark-2.4.8-bin-hadoop2.7/data/mllib/sample_libsvm_data.txt")
    
    println ("Numero de lineas en el archivo de datos:" + data.count ())

### The following line of code will display the first 20 lines of data.
    
    data.show()

### The loaded data is randomly divided into training sets and test sets, in this case with a ratio of 70% - 30%, and the seed is established.
    
    val Array (trainingData, testData) = data.randomSplit (Array (0.7, 0.3), 100L)

### A variable is created to train a Bayesian model.

    val naiveBayesModel = new NaiveBayes().fit(trainingData)

### This model makes the predictions and generates a new DataFrame.

    val predictions = naiveBayesModel.transform(testData)

### Predictions are displayed.

    predictions.show()

### A variable is created that does the evaluation of the model's predictions.

    val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

### A precision variable is created that calculates the precision of the predictions.

    val precision = evaluator.evaluate (predictions)
    Desplegar la tasa de error de acuerdo a las evaluaciones y precisión del modelo.
    println ("tasa de error =" + (1-precision))

