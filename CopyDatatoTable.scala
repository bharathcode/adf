// Databricks notebook source
// MAGIC %sql
// MAGIC use appdb

// COMMAND ----------

// MAGIC %sql
// MAGIC drop table if exists logdata

// COMMAND ----------

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

val filpath = "/FileStore/csv/Log.csv"
val filetype ="csv"


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

val df = spark.read.format(filetype).
options(Map("header"->"true")).

schema(dataSchema).
load(filpath)


// COMMAND ----------

df.write.saveAsTable("logdata")