// Databricks notebook source
import org.apache.spark.sql.types._

val dfjson = spark.read.format("json").load("/FileStore/Json/customer_arr.json")
val newjson = dfjson.select(col("customerid"),col("customername"),col("registered"),explode(col("courses")))
display(newjson)

// COMMAND ----------

val dfjson = spark.read.format("json").load("/FileStore/Json/customer_obj.json")
val newjson = dfjson.select(col("customerid"),col("customername"),col("registered"),explode(col("courses")),
(col("details.city")),col("details.mobile")
)
display(newjson)

// COMMAND ----------

import org.apache.spark.sql.functions._
val dfjson = spark.read.format("json").option("multiline","true").load("/FileStore/Json/CutomerExample.json")
val customerjson=dfjson.select(explode(col("Customers")).alias("Customers"))
val coursesjson=customerjson.select(col("Customers.customerid").alias("CustomerId"),col("Customers.customername").alias("CustomerName"),explode(col("Customers.courses")).alias("Courses"))
display(coursesjson)