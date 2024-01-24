// Databricks notebook source
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

val file_location = "/FileStore/csv/Log.csv"
val file_type = "csv"

val dataSchema = StructType(Array(    
    StructField("Correlationid", StringType, true),
    StructField("Operationname", StringType, true),
    StructField("Status", StringType, true),
    StructField("Eventcategory", StringType, true),
    StructField("Level", StringType, true),
    StructField("Time", TimestampType, true),
    StructField("Subscription", StringType, true),
    StructField("Eventinitiatedby", StringType, true),
    StructField("Resourcetype", StringType, true),
    StructField("Resourcegroup", StringType, true),
    StructField("Resource", StringType, true)))


val df = spark.read.format(file_type).options(Map("header"->"true")).schema(dataSchema).load(file_location)
display(df)

// COMMAND ----------

import org.apache.spark.sql.functions._
display(df.select(year(col("time")).alias("Year"),month(col("time")).alias("Month"),dayofyear(col("time")).alias("Day of Year") ))

// COMMAND ----------

display(df.select(to_date(col("time"),"dd-mm-yyyy").alias("Date")))

// COMMAND ----------

display(df.select(to_date(col("time"),"dd-mm-yyyy").alias("Date")))