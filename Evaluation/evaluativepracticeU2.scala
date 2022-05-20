import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}

// 1. Upload to an Iris dataframe.csv located in https://github.com/jcromerohdz/iris, elaborate the data liempieza necessary to be processed by the following algorithm (Important, this cleaning must be by middle of a Scala script in Spark). 
// A). Use Spark's Mllib library multilayer Machine Learning algorithm perceptron*/
val datairis = spark.read.option("header", "true").option("inferSchema","true")csv("iris.csv")

// 2. What are the names of the columns?
println(datairis.columns.toSeq)

// 3. What is the scheme like?
datairis.printSchema()

// 4. Print the first 5 columns.
datairis.select(datairis.columns.slice(0,5).map(m=>col(m)):_*).show()


