# Evaluation Unit 1

*First we opened the terminal end and wrote "spark-shell".*

### 1. Start a simple Spark session.
    import org.apache.spark.sql.SparkSession
    val sparksession = SparkSession.builder().getOrCreate()

###  2. Upload the Netflix Stock CSV file, make Spark infer the data types.
    val netflixdata = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

###  3. What are the names of the columns?
    netflixdata.columns

###  4. What is the scheme like?
    netflixdata.printSchema()

###  5. Prints the first 5 columns.
    netflixdata.select($"Date",$"Open",$"High",$"Low",$"Close").show()

###  6. Use describe () to learn about the DataFrame.
    netflixdata.describe()

###  7. Create a new dataframe with a new column called "HV Ratio" which is the relationship between the price of the "High" column versus the "Volume" column of shares traded for one day. Hint - is an operation
    var newhvratio = netflixdata.withColumn("HV Ratio", netflixdata("High")/netflixdata("Volume"))

###  8. What day had the highest peak in the "Open" column?
    val daymax = netflixdata.withColumn("Day", dayofmonth(netflixdata("Date")))
    val maxnet = daymax.groupBy("Day").max()
    maxnet.show()
    maxnet.select($"Day",$"max(Open)").show()

###  9. What is the meaning of the Close column "Close" in the context of financial information, explain it there is nothing to encode?
    The "close" column represents the close of a day

###  10. What is the maximum and minimum of the "Volume" column?
    netflixdata.select(max("Volume"), min("Volume")).show()

###  11. With Scala/Spark $ Syntax answer the following:

a. How many days was the "Close" column less than $600?
    
    netflixdata.filter($"Close" < 600).count()

b. What percentage of the time was the "High" column greater than $500?

    (netflixdata.filter($"High" > 500).count().toFloat / netflixdata.count().toFloat) * 100

c. What is the Pearson correlation between the "High" column and the "Volume" column?

    netflixdata.select(corr("High", "Volume")).show()


d. What is the maximum of the "High" column per year?
val colYear = df.withColumn(“Years, year(df("Date")))

    val dfYearMax = colAnio.groupBy(“Years”).max()

    dfYearMax.select($”Years”, $"max(High)").show()


e. What is the "Close" column average for each calendar month?
val colMonth = df.withColumn("Mes", month(df("Date")))

    val dfAverage = colMonth.groupBy("Mes").mean()

    dfAverage.select($"Mes", $"avg(Close)").show()
