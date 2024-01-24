// Databricks notebook source
spark.conf.set(
  "fs.azure.account.key.aaasproduction14122023.dfs.core.windows.net",
  "g2xpKz78SXPiQUVbYKsjW7IU7/oFtaXnpbH9JClni3dL+YlsMTIfGBc2QN+u77wyPrCTHdnKf5aK+AStOS3VwQ=="
)

// COMMAND ----------


val df = spark.read
  .format("com.databricks.spark.sqldw")
  .option("url", "jdbc:sqlserver://aaasproduction14122023.sql.azuresynapse.net:1433;database=aaasdw")
  .option("user","aaasadmin")
  .option("password","Welcome@123")
  .option("tempDir", "abfss://staging@aaasproduction14122023.dfs.core.windows.net/databricks")  
  .option("forwardSparkAzureStorageCredentials", "true")
  .option("dbTable", "FactSales")
  .load()

// COMMAND ----------

display(df)