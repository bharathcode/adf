// Databricks notebook source
// MAGIC %sql
// MAGIC drop table dimcustomer;
// MAGIC create table dimcustomer(
// MAGIC   CustomerID STRING,
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

val dfDimcustomer= (spark.readStream.format("cloudfiles").option("cloudfiles.schemalocation",checkpointpath).option("cloudfiles.format","csv").load(path))
val finaldimcustomer = dfDimcustomer.dropDuplicates("CustomerID")
finaldimcustomer.writeStream.format("delta").option("checkpointLocation",checkpointpath).option("mergeSchema","true").table("Dimcustomer")


// COMMAND ----------

// MAGIC %sql
// MAGIC select * from dimcustomer

// COMMAND ----------

// MAGIC %sql
// MAGIC delete from dimcustomer

// COMMAND ----------

val dfDimcustomer= (spark.readStream.format("cloudfiles").option("cloudfiles.schemalocation",checkpointpath).option("cloudfiles.format","csv").load(path))
val finaldimcustomer = dfDimcustomer.dropDuplicates("CustomerID")
finaldimcustomer.writeStream.format("delta").option("checkpointLocation",checkpointpath).option("mergeSchema","true").table("Dimcustomer")

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from Dimcustomer

// COMMAND ----------

// MAGIC %sql
// MAGIC select customerid, count(0) as a from Dimcustomer
// MAGIC group by customerid
// MAGIC having a>1;