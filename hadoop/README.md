# Hadoop Project

Table of contents
- [Introduction](#introduction)
- [Hadoop Cluster](#hadoop-cluster)
- [Hive Project](#hive-project)
- [Improvements](#improvements)

# Introduction

**The Hadoop project** was created to help data analytics team process big data 
using Apache Hadoop and Apache Hive. The World Development Indicators dataset,
which contains approximately 22 million data points was used in this project.

A 3-node **The Hadoop cluster** was provisioned with **Google Cloud Platform** Dataproc.
All queries and their results were saved in **Zeppelin** notebook on Hadoop cluster's web interfaces.


# Hadoop Cluster

A Hadoop cluster is set up on Google Could Platform with 1 master and 2 worker nodes.

### Cluster architecture diagram

![](assets/hadoop_cluster.png)

### Big data tools evaluated

* **Hadoop Distributed File System (HDFS)** is the primary data storage system used by Hadoop applications. 
HDFS employs a NameNode and DataNode architecture to implement a distributed file system that provides 
high-performance access to data across highly scalable Hadoop clusters.
  With the HDFS the data is written once on the server and subsequently read and re-used many times thereafter.
* **Yet Another Resource Negotiator (YARN)** provides its core services via two types of long-running daemon:
a _resource manager_ (one per cluster) to manage the use of resources across the cluster, and _node
  managers_ running on all the nodes in the cluster to launch and monitor containers. A
  container executes an application-specific process with a constrained set of resources
  (memory, CPU, and so on).
* **Zeppelin** is an interactive notebook, which allows the user to manipulate the data(data ingestion,
data exploration, visualization, sharing and collaboration) using Hadoop and Spark.
* **MapReduce** works by breaking the parallel processing (provided by Hadoop) into two phases: the map phase and the
  reduce phase. Each phase has key-value pairs as input and output, the types of which may
  be chosen by the programmer. The programmer also specifies two functions: the map
  function and the reduce function.
* **Hive** is an open-source system that processes (reads, writes, manage) large structured data in Hadoop, 
residing in distributed storage using SQL.
* **Dataproc** is a managed Spark and Hadoop service that allows to take advantage of open source data tools
for batch processing, querying, streaming, and machine learning. Dataproc automation helps to create clusters
quickly, manage them easily, and save money by turning clusters off when the user doesn't need them.

### Hardware Specifications

* 1 master node - contains the YARN Resource Manager, HDFS NameNode, and all job drivers
* 2 worker nodes - each contains a YARN NodeManager and HDFS DateNode
* 2 cores on each node
* 12 GB RAM
* 100 GB of disk space

# Hive Project

Working with Big Data is always important to use optimization tools. We worked with the World 
Development Indicators dataset (WDI_2016), a 2.21 GB file, with 21,759,408 rows. The dataset was 
exported from GCP Storage to HDFS. We examined the data in created Hive tables using following 
optimization tools:

* **Partitioning** divides the table into smaller parts based on the values of a particular column (keys).
  It allows Hive to skip all but relevant sub-directories and files.
* **Columnar File Optimization (parquet)** allows to store data in a column-oriented format 
(all columns are adjacent) enabling better compression and faster data retrieval. 
* **Spark.sql** processes data in available RAM reducing the read and write time. 
However, Spark can only work with data that can fit into distributed RAM.
* **SerDe** allows Hive to read the data from a table, and write it back to HDFS in any custom format.
However, SerDe has a limitation of a datatype it can work with. It only accepts String. To solve it, the user
will need to create a view and cast String columns as other datatype.

#### [My Zeppelin Notebook with Hive queries](hive/hive.ipynb)

![](assets/hadoop.png)

# Improvements

* Use appropriate file formats such as the Optimized Row Columnar (ORC) to increase query performance. 
ORC reduces the original data size by up to 75 percent
* Create a separate index table that functions as a quick reference for the original table
* Divide table sets into more manageable parts with bucketing