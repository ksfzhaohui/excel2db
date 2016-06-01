@echo off
echo ========================================================
echo  支持Excel97以上版本,后缀为.xls和.xlsx,稍后会有更多支持!
echo ========================================================

set language=java
set beanRoot=D:\ndbtest\excelPath\bean
set packageRoot=test
set excelPath=D:\ndbtest\excelPath
set ndbPath=D:\ndbtest\ndb

java -jar excel2db-write-0.0.1-SNAPSHOT.jar %language% %beanRoot% %packageRoot% %excelPath% %ndbPath%
pause