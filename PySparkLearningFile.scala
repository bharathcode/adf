// Databricks notebook source
// MAGIC %python
// MAGIC data = [[295, "South Bend", "Indiana", "IN", 101190, 112.9]]
// MAGIC columns = ["rank", "city", "state", "code", "population", "price"]
// MAGIC
// MAGIC df1 = spark.createDataFrame(data, schema="rank LONG, city STRING, state STRING, code STRING, population LONG, price DOUBLE")
// MAGIC display(df1)

// COMMAND ----------

// MAGIC %python
// MAGIC df2 = (spark.read
// MAGIC   .format("csv")
// MAGIC   .option("header", "true")
// MAGIC   .option("inferSchema", "true")
// MAGIC   .load("/databricks-datasets/samples/population-vs-price/data_geo.csv")
// MAGIC )

// COMMAND ----------

// MAGIC %python
// MAGIC df = df1.union(df2)

// COMMAND ----------

// MAGIC %python
// MAGIC display(df)

// COMMAND ----------

// MAGIC %python
// MAGIC df.printSchema()

// COMMAND ----------

// MAGIC %python
// MAGIC filtered_df = df.filter(df["rank"] < 6)
// MAGIC display(filtered_df)
// MAGIC filtered_df = df.where(df["rank"] < 6)
// MAGIC display(filtered_df)

// COMMAND ----------

// MAGIC %python
// MAGIC select_df = df.select("City", "State")
// MAGIC display(select_df)

// COMMAND ----------

// MAGIC %python
// MAGIC subset_df = df.filter(df["rank"] < 11).select("City")
// MAGIC display(subset_df)