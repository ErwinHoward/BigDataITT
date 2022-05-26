Imports 

    import org.apache.spark.ml.Pipeline
    import org.apache.spark.ml.classification.DecisionTreeClassifier
    import org.apache.spark.ml.classification.DecisionTreeClassificationModel
    import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
    import org.apache.spark.mllib.util.MLUtils

Load and parse the data file into a DataFrame.

    val data = MLUtils.loadLibSVMFile(sc, "data/mllib/sample_libsvm_data.txt").toDF()

Add the metadata to the label column. Adjust the dataset to include all the column labels in the index.

    val labelIndexer = new StringIndexer()
    .setInputCol("label")
    .setOutputCol("indexedLabel")
    .fit(data)

Split the data into training and test sets (70% - 30% ratio)

    val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

Create the DecisionTree model.    

    val dt = new DecisionTreeClassifier()
    .setLabelCol("indexedLabel")
    .setFeaturesCol("indexedFeatures")

Convert indexed tags back to original tags.

    val labelConverter = new IndexToString()
    .setInputCol("prediction")
    .setOutputCol("predictedLabel")
    .setLabels(labelIndexer.labels)

Chain indexers and the tree created in a Pipeline.

    val pipeline = new Pipeline()
    .setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

Train the model and run the indexers.
    
    val model = pipeline.fit(trainingData)

Create prediction.

    val predictions = model.transform(testData)

Select the columns that you want to display.

    predictions.select("predictedLabel", "label", "features").show(5)

Select prediction, label, and test error.

    val evaluator = new MulticlassClassificationEvaluator()
    .setLabelCol("indexedLabel")
    .setPredictionCol("prediction")
    .setMetricName("precision")
    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))
    
    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    println("Learned classification tree model:\n" + treeModel.toDebugString)

