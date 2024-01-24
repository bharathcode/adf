// Databricks notebook source
// MAGIC %sql
// MAGIC DROP TABLE dimcustomer;
// MAGIC create table dimcustomer(
// MAGIC   CustomerID INT,
// MAGIC   CompanyName string,
// MAGIC   SalesPerson string
// MAGIC
// MAGIC )

// COMMAND ----------

spark.conf.set(
  "fs.azure.account.key.aaasproduction14122023.dfs.core.windows.net",
  "g2xpKz78SXPiQUVbYKsjW7IU7/oFtaXnpbH9JClni3dL+YlsMTIfGBc2QN+u77wyPrCTHdnKf5aK+AStOS3VwQ=="
)

val path = "abfss://csv@aaasproduction14122023.dfs.core.windows.net/customer/"
val checkpointpath ="abfss://checkpoint@aaasproduction14122023.dfs.core.windows.net/"
val schemalocation ="abfss://schema@aaasproduction14122023.dfs.core.windows.net/"

// COMMAND ----------

val dataSchema = StructType(Array(
  StructField("CustomerID",IntegerType, true),
  StructField("CompanyName",StringType, true),
  StructField("SalesPerson",StringType,true)
))

// COMMAND ----------


val dfDimcustomer= (spark.readStream.format("cloudfiles").schema(dataSchema).option("header","true").option("cloudfiles.format","csv").load(path))
dfDimcustomer.writeStream.format("delta").option("checkpointLocation",checkpointpath).option("mergeSchema","true").table("Dimcustomer")


// COMMAND ----------

// MAGIC %sql
// MAGIC select * from dimcustomer;