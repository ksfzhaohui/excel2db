@echo off
set language=csharp
set beanRoot=D:\ndbtest\excelPath\bean
set nameSpace=bean
set excelPath=D:\ndbtest\excelPath
set ndbPath=D:\ndbtest\ndb

java -jar excel2db-write-0.0.1-SNAPSHOT.jar %language% %beanRoot% %nameSpace% %excelPath% %ndbPath%
pause