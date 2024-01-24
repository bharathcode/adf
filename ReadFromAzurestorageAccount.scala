// Databricks notebook source
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._


val file_location = "abfss://csv@aaasproduction14122023.dfs.core.windows.net/Log.csv"

val file_type = "csv"

spark.conf.set(
  "fs.azure.account.key.aaasproduction14122023.dfs.core.windows.net",
  "g2xpKz78SXPiQUVbYKsjW7IU7/oFtaXnpbH9JClni3dL+YlsMTIfGBc2QN+u77wyPrCTHdnKf5aK+AStOS3VwQ=="
)

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


val df = spark.read.format(file_type).
options(Map("header"->"true")).
schema(dataSchema).
load(file_location)
display(df)