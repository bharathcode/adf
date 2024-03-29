// Databricks notebook source
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._

val file_location = "/FileStore/parque/log.parquet"
val file_type = "parquet"

val dataSchema = StructType(Array(    
    StructField("Correlationid", StringType, true),
    StructField("Operationname", StringType, true),
    StructField("Status", StringType, true),
    StructField("Eventcategory", StringType, true),
    StructField("Level", StringType, true),
    StructField("Time", StringType, true),
    StructField("Subscription", StringType, true),
    StructField("Eventinitiatedby", StringType, true),
    StructField("Resourcetype", StringType, true),
    StructField("Resourcegroup", StringType, true),
    StructField("Resource", StringType, true)))


val df = spark.read.format(file_type).options(Map("header"->"true")).schema(dataSchema).load(file_location)
display(df)


// COMMAND ----------

display(df.groupBy(df("Operationname")).count())

// COMMAND ----------

display(df.groupBy(df("Operationname")).
count().alias("Count").
filter(col("Count")>100))