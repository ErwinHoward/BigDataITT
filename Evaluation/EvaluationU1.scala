//First we opened the terminal end and wrote "spark-shell".

//1. Start a simple Spark session.
import org.apache.spark.sql.SparkSession
val sparksession = SparkSession.builder().getOrCreate()

//2. Upload the Netflix Stock CSV file, make Spark infer the data types.
val netflixdata = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

//3. What are the names of the columns?
netflixdata.columns
Answer: 
Date, Open, High, Low, Close, Volume, Adj Close

//4. What is the scheme like?
netflixdata.printSchema()
Answer: 
Date: timestamp (nullable = true)
Open: double (nullable = true)
High: double (nullable = true)
Low: double (nullable = true)
Close: double (nullable = true)
Volume: integer (nullable = true)
Adj Close: double (nullable = true)

//5. Prints the first 5 columns.
netflixdata.select($"Date",$"Open",$"High",$"Low",$"Close").show()
Answer:
+-------------------+-----------------+------------------+----------+-----------------+
|               Date|             Open|              High|       Low|            Close|
+-------------------+-----------------+------------------+----------+-----------------+
|2011-10-24 00:00:00|       119.100002|120.28000300000001|115.100004|       118.839996|
|2011-10-25 00:00:00|        74.899999|         79.390001| 74.249997|        77.370002|
|2011-10-26 00:00:00|            78.73|         81.420001| 75.399997|        79.400002|
|2011-10-27 00:00:00|        82.179998| 82.71999699999999| 79.249998|80.86000200000001|
|2011-10-28 00:00:00|        80.280002|         84.660002| 79.599999|84.14000300000001|
|2011-10-31 00:00:00|83.63999799999999|         84.090002| 81.450002|        82.080003|
|2011-11-01 00:00:00|        80.109998|         80.999998|     78.74|        80.089997|
|2011-11-02 00:00:00|        80.709998|         84.400002| 80.109998|        83.389999|
|2011-11-03 00:00:00|        84.130003|         92.600003| 81.800003|        92.290003|
|2011-11-04 00:00:00|91.46999699999999| 92.89000300000001| 87.749999|        90.019998|
|2011-11-07 00:00:00|             91.0|         93.839998| 89.979997|        90.830003|
|2011-11-08 00:00:00|91.22999899999999|         92.600003| 89.650002|        90.470001|
|2011-11-16 00:00:00|        86.460003|         86.460003| 80.890002|        81.180002|
|2011-11-17 00:00:00|            80.77|         80.999998| 75.789999|        76.460001|
|2011-11-18 00:00:00|             76.7|         78.999999| 76.039998|        78.059998|
+-------------------+-----------------+------------------+----------+-----------------+
only showing top 20 rows 

//6. Use describe () to learn about the DataFrame.
netflixdata.describe()
Answer: 
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|summary|              Open|              High|               Low|             Close|              Volume|         Adj Close|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|  count|              1259|              1259|              1259|              1259|                1259|              1259|
|   mean|230.39351086656092|233.97320872915006|226.80127876251044|  230.522453845909|2.5634836060365368E7|55.610540036536875|
| stddev|164.37456353264244| 165.9705082667129| 162.6506358235739|164.40918905512854| 2.306312683388607E7|35.186669331525486|
|    min|         53.990001|         55.480001|             52.81|              53.8|             3531300|          7.685714|
|    max|        708.900017|        716.159996|        697.569984|        707.610001|           315541800|        130.929993|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+

//7. Create a new dataframe with a new column called "HV Ratio" which is the relationship between the price of the "High" column versus the "Volume" column of shares traded for one day. Hint - is an operation
var newhvratio = netflixdata.withColumn("HV Ratio", netflixdata("High")/netflixdata("Volume"))

//8. What day had the highest peak in the "Open" column?
val daymax = netflixdata.withColumn("Day", dayofmonth(netflixdata("Date")))
val maxnet = daymax.groupBy("Day").max()
maxnet.show()
maxnet.select($"Day",$"max(Open)").show()
Answer:
+---+-----------------+
|Day|        max(Open)|
+---+-----------------+
| 31|       430.259995|
| 28|       628.000008|
| 26|       667.100021|
| 27|       617.000023|
| 12|664.4100269999999|
| 22|        665.29998|
|  1|663.6400219999999|
| 13|686.6900019999999|
| 16|       659.700012|
|  6|       654.309982|
|  3|       624.700012|
| 20|       617.500008|
|  5|       624.500008|
| 19|       673.700012|
| 15|       649.999977|
|  9|       664.300011|
| 17|       665.930008|
|  4|       618.649994|
|  8|       654.299995|
| 23|       674.350014|
+---+-----------------+
only showing top 20 rows

//9. What is the meaning of the Close column "Close" in the context of financial information, explain it there is nothing to encode?
Answer:
The "close" column represents the close of a day

//10. What is the maximum and minimum of the "Volume" column?
netflixdata.select(max("Volume"), min("Volume")).show()
+-----------+-----------+
|max(Volume)|min(Volume)|
+-----------+-----------+
|  315541800|    3531300|
+-----------+-----------+

//11. With Scala/Spark $ Syntax answer the following:
//a. How many days was the "Close" column less than $600?
netflixdata.filter($"Close" < 600).count()
Answer: 
Long = 1218
//b. What percentage of the time was the "High" column greater than $500?
(netflixdata.filter($"High" > 500).count().toFloat / netflixdata.count().toFloat) * 100
Answer: Float = 4.9245434
//c. What is the Pearson correlation between the "High" column and the "Volume" column?
//d. What is the maximum of the "High" column per year?
//e. What is the "Close" column average for each calendar month?