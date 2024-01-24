-- Databricks notebook source
create database appdb

-- COMMAND ----------

use appdb

-- COMMAND ----------

create table logdata

-- COMMAND ----------

-- MAGIC %scala
-- MAGIC spark.conf.set(
-- MAGIC   "fs.azure.account.key.aaasproduction14122023.dfs.core.windows.net",
-- MAGIC   "g2xpKz78SXPiQUVbYKsjW7IU7/oFtaXnpbH9JClni3dL+YlsMTIfGBc2QN+u77wyPrCTHdnKf5aK+AStOS3VwQ=="
-- MAGIC )

-- COMMAND ----------

-- MAGIC %sql
-- MAGIC copy into logdata
-- MAGIC from 'abfss://parquet@aaasproduction14122023.dfs.core.windows.net/log_parquet.parquet'
-- MAGIC FILEFORMAT =PARQUET
-- MAGIC COPY_OPTIONS('mergeSchema'='true')

-- COMMAND ----------

-- MAGIC %sql
-- MAGIC COPY INTO logdata
-- MAGIC FROM 'abfss://parquet@aaasproduction14122023.dfs.core.windows.net/log.parquet'
-- MAGIC FILEFORMAT = PARQUET
-- MAGIC COPY_OPTIONS ('mergeSchema' = 'true');

-- COMMAND ----------

select * from logdata