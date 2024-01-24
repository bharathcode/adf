// Databricks notebook source
import org.apache.spark.sql.types._
spark.conf.set(
  "fs.azure.account.key.aaasproduction14122023.dfs.core.windows.net",
  "g2xpKz78SXPiQUVbYKsjW7IU7/oFtaXnpbH9JClni3dL+YlsMTIfGBc2QN+u77wyPrCTHdnKf5aK+AStOS3VwQ=="
)

val path = "abfss://csv@aaasproduction14122023.dfs.core.windows.net/customer/"
val checkpointpath ="abfss://checkpoint@aaasproduction14122023.dfs.core.windows.net/"
val schemalocation ="abfss://schema@aaasproduction14122023.dfs.core.windows.net/"

val dataSchema = StructType(Array(    
    StructField("CustomerID", IntegerType, true),
    StructField("CompanyName", StringType, true),
    StructField("SalesPerson", StringType, true)))


// COMMAND ----------

val dfDimCustomer=(spark.readStream.format("cloudfiles")
    .schema(dataSchema)    
    .option("cloudFiles.format","csv")
    .option("header",true)
    .load(path))


// COMMAND ----------

  val checkpointpath ="abfss://checkpoint@aaasproduction14122023.dfs.core.windows.net/"
  dfDimCustomer.writeStream
  .format("com.databricks.spark.sqldw")
  .option("url", "jdbc:sqlserver://aaasproduction14122023.sql.azuresynapse.net:1433;database=aaasdw")
  .option("user","aaasadmin")
  .option("password","Welcome@123")
  .option("tempDir", "abfss://staging@aaasproduction14122023.dfs.core.windows.net/databricks")  
  .option("forwardSparkAzureStorageCredentials", "true")
  .option("dbTable", "DimCustomer")
  .option("checkpointLocation", checkpointpath) 
  .start()