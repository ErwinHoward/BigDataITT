# Evaluation Unit 1

*First we opened the terminal end and wrote "spark-shell".*

### 1. Start a simple Spark session.
    Import and create the spark sql session.
    
    import org.apache.spark.sql.SparkSession
    val sparksession = SparkSession.builder().getOrCreate()

###  2. Upload the Netflix Stock CSV file, make Spark infer the data types.
    Netflix Stock CVS file data loaded

    val netflixdata = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

###  3. What are the names of the columns?
    Use the name that was assigned to the dataframe, followed by “.columns” so that it returns the column names in an array.

    netflixdata.columns

###  4. What is the scheme like?
    .printSchema() was used to get the schema of the dataframe.

    netflixdata.printSchema()

###  5. Prints the first 5 columns.
    It is necessary to name the name of the columns that you want to print, for later show them with .show()

    netflixdata.select($"Date",$"Open",$"High",$"Low",$"Close").show()

###  6. Use describe () to learn about the DataFrame.
    Information is obtained from the file data

    netflixdata.describe().show()

###  7. Create a new dataframe with a new column called "HV Ratio" which is the relationship between the price of the "High" column versus the "Volume" column of shares traded for one day. Hint - is an operation
    Creation of a new dataframe, the HV Ratio column is assigned the value obtained from the High columns divided by the Volume of the “netflixdata” dataframe.

    var newhvratio = ("HV Ratio", netflixdata("High")/netflixdata("Volume"))

###  8. What day had the highest peak in the "Open" column?
    The daymax variable is declared, which contains the days of the “Date” column of the “netflixdata” dataframe.
    
    The variable maxnet is assigned the value of maxday, grouped by day and ordered from highest to lowest.
    
    Finally, a select is used to display the largest value, in this case it is the 31st.

    val daymax = netflixdata.withColumn("Day", dayofmonth(netflixdata("Date")))
    val maxnet = daymax.groupBy("Day").max()
    maxnet.show()
    maxnet.select($"Day",$"max(Open)").show()

###  9. What is the meaning of the Close column "Close" in the context of financial information, explain it there is nothing to encode?
    
    The "close" column represents the close of a day

###  10. What is the maximum and minimum of the "Volume" column?
    The maximum and the minimum of the "Close" table are shown

    netflixdata.select(max("Volume"), min("Volume")).show()

###  11. With Scala/Spark $ Syntax answer the following:

a. How many days was the "Close" column less than $600?
    To obtain the result, a filter is applied to the “Close” column, where the value must be less than 600.

    netflixdata.filter($"Close" < 600).count()

b. What percentage of the time was the "High" column greater than $500?
    Similarly, a filter is applied to the "Close" column, where the value must be greater than 500, and to obtain the result it is multiplied by 100.

    (netflixdata.filter($"High" > 500).count().toFloat / netflixdata.count().toFloat) * 100

c. What is the Pearson correlation between the "High" column and the "Volume" column?
    To obtain the Pearson correlation, the "corr" method is used, where the columns between which it will be calculated are specified.

    netflixdata.select(corr("High", "Volume")).show()


d. What is the maximum of the "High" column per year?
    Se declara la variable colYear, a la cual se le asigna el valor de la columna “Date” del dataframe netflixdata”.
    
    Se declara la variable dfYearMax, la cual agrupa por año los valores de colYear y los ordena de mayor a menor.
    
    Por último, para imprimir los valores, se usa un select que contenga la información de los años (Years) y la cantidad máxima max(High).
    
    val colYear = netflixdata.withColumn("Years", year(netflixdata("Date")))

    val dfYearMax = colYear.groupBy("Years").max()

    dfYearMax.select($"Years", $"max(High)").show()


e. What is the "Close" column average for each calendar month?
    The colMonth variable is declared, to which the value of the "Date" column of the dataframe netflixdata”.
    
    The variable dfAverage is declared, which groups the values ​​of colMonth by month. 

    Finally, to print the values, a select is used that contains the information of the months (Month) and the average avg(Close).

    val colMonth = netflixdata.withColumn("Mes", month(netflixdata("Date")))

    val dfAverage = colMonth.groupBy("Mes").mean()

    dfAverage.select($"Mes", $"avg(Close)").show()

