### Introduction
***RUN APP***
```scala
$ sbt run
```
Worker APP dependents on reactiveJobQueue App. Before run worker app compile and do publish local to reactiveJobQueue.

```bash
$ cd reactiveJobQueue $ sbt publishLocal
```

***Create kafka topic***

```bash
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic uploadmessage
```
```SQL
Create MySql database & Table.

Create database userdb;

Use userdb;

create table userinfo(id varchar(255) primary key,name varchar(255), time_of_start varchar(255));
```